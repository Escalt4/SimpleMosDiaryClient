package ru.example.simplemosdiaryclient.view;


import static androidx.annotation.Dimension.DP;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import ru.example.simplemosdiaryclient.R;
import ru.example.simplemosdiaryclient.database.SimpleMosDiaryClientDatabase;
import ru.example.simplemosdiaryclient.network.ApiService;
import ru.example.simplemosdiaryclient.network.RetrofitClient;
import ru.example.simplemosdiaryclient.network.network_entity.Mark;
import ru.example.simplemosdiaryclient.network.network_entity.Schedule;
import ru.example.simplemosdiaryclient.network.network_entity.ShortSchedule;
import ru.example.simplemosdiaryclient.network.network_entity.StudentProfile;
import ru.example.simplemosdiaryclient.network.network_entity.schedule.Activity;
import ru.example.simplemosdiaryclient.network.network_entity.short_schedule.Lesson;
import ru.example.simplemosdiaryclient.network.network_entity.short_schedule.ScheduleItem;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    static final String LOG_TAG = "MyApp";
    static final Integer[] DAYS_OF_WEEK = {6, 0, 1, 2, 3, 4, 5};
    static final Integer[] TABS = {0, 1, 2, 3, 4, 5, 0};
    //    static final String[] TABS_NAMES = {"ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ", "ВС"};
    static final String[] TABS_NAMES = {"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"};
    static final String[] MONTHS_NAMES = {"Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек"};
    //
    SharedPreferences settings;
    SharedPreferences.Editor prefEditor;
    //
//    TextView textViewTimer;
//    Button buttonToCurrentDate;
//    ViewPager2 pager;
    TabLayout tabLayout;
    //
//    Pair[][][][] timetable;
//    MyTime[][][] callsSchedule;
//
//    Integer lastHighlightWeek = -1;
//    Integer lastHighlightDay = -1;
//    Integer lastHighlightPair = -1;
//    Integer lastHighlightBreak = -1;

    Calendar curDate;

    //    Calendar curCalendar;           // обьект текущей даты
//    Calendar changeCalendar;        // обьект выбраной даты
//    Integer curWeekType;            // текущая неделя верхняя или нижняя
//    Integer changeWeekType;         // выбраная неделя верхняя или нижняя
    Integer curWeekNum = -1;             // номер текущей недели
    //    Integer changeWeekNum;          // номер выбраной недели
//    Integer curDayOfWeek;           // текущий день недели
    Integer changeDayOfWeek = 0;        // выбраный день недели
//    Integer[] changeWeekDays;       // список дней выбраной недели
//    Integer[] changeWeekMonth;      // список месяцев дней выбраной недели
//    Integer changeGroup;            // выбраная группа
//    Integer changeTab = 0;          // выбраная вкладка
//    String brakeBlockLabel;
//    String timerText;

    private String token = "";
    private Integer studentId = -1;
    private String studentName = "";
    private String studentGroup = "";

    ViewPager2 viewPager;
    SimpleMosDiaryClientDatabase db;

    Context context;
    List<String> dateList = new ArrayList<>();
    List<Integer> dayList = new ArrayList<>();

    @Override
    protected void onPause() {
        super.onPause();

        ((SwipeRefreshLayout) findViewById(R.id.swipeContainer)).setRefreshing(false);
        if (d != null && !d.isDisposed()) d.dispose();
    }

    DataController dataController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        dataController = DataController.getInstance();

        db = SimpleMosDiaryClientDatabase.getInstance(getApplicationContext());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        settings = getSharedPreferences("Settings", MODE_PRIVATE);
        prefEditor = settings.edit();

        token = settings.getString("TOKEN", "");
        if (token.isEmpty()) showTokenDialog();
        else initHeader();

        ((SwipeRefreshLayout) findViewById(R.id.swipeContainer)).setOnRefreshListener(() -> {
            requestShortSchedule(dateList);
        });

        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new ViewPagerAdapter(this));
        viewPager.setCurrentItem(TABS[(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + 5) % 7], false);


        curDate = Calendar.getInstance();
        findViewById(R.id.buttonWeekDown).setOnClickListener(view -> {
            curDate.add(Calendar.WEEK_OF_MONTH, -1);
            setWeek();
        });
        findViewById(R.id.buttonWeekUp).setOnClickListener(view -> {
            curDate.add(Calendar.WEEK_OF_MONTH, 1);
            setWeek();
        });

        setWeek();
    }

    private void setWeek() {
        dateList = new ArrayList<>();
        dayList = new ArrayList<>();
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(curDate.getTimeInMillis());
        calendar.add(Calendar.DATE, -(calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7);

        String[] months = new String[]{"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

        int m = calendar.get(Calendar.MONTH);
        String curMonth = months[calendar.get(Calendar.MONTH)];
        calendar.add(Calendar.DATE, 6);
        if (m != calendar.get(Calendar.MONTH))
            curMonth += "  -  " + months[calendar.get(Calendar.MONTH)];
        calendar.add(Calendar.DATE, -6);

        ((TextView) findViewById(R.id.textViewTimer)).setText(curMonth);
        for (int i = 0; i < 6; i++) {
            dayList.add(calendar.get(Calendar.DAY_OF_MONTH));
            dateList.add(timeFormat.format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }

        tabLayout = findViewById(R.id.tab_layout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(TABS_NAMES[position] + "\n" + dayList.get(position)));
        tabLayoutMediator.attach();

        dataController.setData(dateList);

        if (token != "") {
            Long lastUpdateDate = settings.getLong("UPDATE_DATE_" + String.join(",", dateList), -1);
            if (Math.abs(Calendar.getInstance().getTimeInMillis() - lastUpdateDate) > 5 * 60 * 1000) {
                requestShortSchedule(dateList);
            }
        }
    }

    Disposable d;

    private void requestShortSchedule(List<String> dateList) {
        if (dateList.size() == 0) return;
        ((SwipeRefreshLayout) findViewById(R.id.swipeContainer)).setRefreshing(true);

        try {
            ApiService apiServiceSchool = RetrofitClient.getSchoolClient().create(ApiService.class);
            ApiService apiServiceDnevnik = RetrofitClient.getDnevnikClient().create(ApiService.class);

            Observable<ShortSchedule> observable0 = apiServiceSchool.getShortSchedule(token, "familymp", studentId, String.join(",", dateList));
            Observable<Schedule> observable1 = apiServiceSchool.getSchedule(token, "familymp", studentId, dateList.get(0));
            Observable<Schedule> observable2 = apiServiceSchool.getSchedule(token, "familymp", studentId, dateList.get(1));
            Observable<Schedule> observable3 = apiServiceSchool.getSchedule(token, "familymp", studentId, dateList.get(2));
            Observable<Schedule> observable4 = apiServiceSchool.getSchedule(token, "familymp", studentId, dateList.get(3));
            Observable<Schedule> observable5 = apiServiceSchool.getSchedule(token, "familymp", studentId, dateList.get(4));
            Observable<Schedule> observable6 = apiServiceSchool.getSchedule(token, "familymp", studentId, dateList.get(5));

            SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
            Observable<List<Mark>> observable00 = apiServiceDnevnik.getMarks(
                    "auth_token=" + token + ";student_id=" + studentId,
                    dateList.get(0), dateList.get(5), studentId);

            if (d != null && !d.isDisposed()) d.dispose();

            d = Observable.zip(observable0, observable1, observable2, observable3, observable4, observable5, observable6, observable00, (response0, response1, response2, response3, response4, response5, response6, response00) -> {
                List<ru.example.simplemosdiaryclient.database.database_entity.Lesson> lessonList = new ArrayList<>();

                List<ScheduleItem> scheduleItemList0 = response0.getPayload();
                for (ScheduleItem scheduleItem : scheduleItemList0) {
                    for (Lesson lesson : scheduleItem.getLessons()) {
                        lessonList.add(new ru.example.simplemosdiaryclient.database.database_entity.Lesson(scheduleItem.getDate(), lesson.getScheduleItemId(), lesson.getSubjectName(), lesson.getGroupName(), lesson.getBeginTime(), lesson.getEndTime(), lesson.getAbsenceReasonId()));
                    }
                }

                List<Activity> activityList = new ArrayList<>();
                activityList.addAll(response1.getActivities());
                activityList.addAll(response2.getActivities());
                activityList.addAll(response3.getActivities());
                activityList.addAll(response4.getActivities());
                activityList.addAll(response5.getActivities());
                activityList.addAll(response6.getActivities());

                for (Activity activity : activityList) {
                    if (Objects.equals(activity.getType(), "LESSON")) {
                        long scheduleItemId = activity.getLesson().getScheduleItemId();

                        ru.example.simplemosdiaryclient.database.database_entity.Lesson lessonListElem = lessonList.stream().filter(obj -> obj.getScheduleItemId().equals(scheduleItemId)).findFirst().get();

                        lessonListElem.setLessonNum(Integer.parseInt(activity.getInfo().substring(0, 2).trim()));
                        lessonListElem.setCabinetNum(activity.getRoomNumber());
                        lessonListElem.setTeacherFirstName(activity.getLesson().getTeacher().getFirstName());
                        lessonListElem.setTeacherLastName(activity.getLesson().getTeacher().getLastName());
                        lessonListElem.setTeacherMiddleName(activity.getLesson().getTeacher().getMiddleName());
                        lessonListElem.setTeacherMiddleName(activity.getLesson().getTeacher().getMiddleName());
                    }
                }


                List<ru.example.simplemosdiaryclient.database.database_entity.Lesson> lessonList0 = new ArrayList<>();
                for (ru.example.simplemosdiaryclient.database.database_entity.Lesson lesson : lessonList) {
                    boolean find = false;

                    for (ru.example.simplemosdiaryclient.database.database_entity.Lesson lesson0 : lessonList0) {
                        if (Objects.equals(lesson.getSubject(), lesson0.getSubject()) && Objects.equals(lesson.getCabinetNum(), lesson0.getCabinetNum()) && Objects.equals(lesson.getGroupName(), lesson0.getGroupName()) && Objects.equals(lesson.getTeacherMiddleName(), lesson0.getTeacherMiddleName()) && Objects.equals(lesson.getTeacherFirstName(), lesson0.getTeacherFirstName()) && Objects.equals(lesson.getTeacherLastName(), lesson0.getTeacherLastName()) && Objects.equals(lesson.getLessonDate(), lesson0.getLessonDate()) && Math.abs(lesson.getLessonNum() - lesson0.getLessonNum()) == 1) {
                            lesson0.setLessonNum(Math.max(lesson0.getLessonNum(), lesson.getLessonNum()) / 2);
                            lesson0.setScheduleItemId2(lesson.getScheduleItemId());
                            lesson0.setTimeStartString(lesson0.getLessonNum() > lesson.getLessonNum() ? lesson.getTimeStartString() : lesson0.getTimeStartString());
                            lesson0.setTimeEndString(lesson0.getLessonNum() > lesson.getLessonNum() ? lesson0.getTimeEndString() : lesson.getTimeEndString());
                            find = true;
                            break;
                        }
                    }

                    if (!find) lessonList0.add(lesson);
                }

                List<ru.example.simplemosdiaryclient.database.database_entity.Mark> markList = new ArrayList<>();

                List<Mark> markList0 = response00;
                for (Mark mark : markList0) {
                    ru.example.simplemosdiaryclient.database.database_entity.Lesson lessonListElem = lessonList.stream().filter(obj -> obj.getScheduleItemId().equals(mark.getScheduleLessonId())).findFirst().orElse(null);
                    if (lessonListElem != null)
                        lessonListElem.setMarkValue(mark.getValues().get(0).getGrade().getFive());
                }

                Executors.newSingleThreadExecutor().execute(() -> {
                    db.simpleMosDiaryClientDao().insertLessonList(lessonList0);
                });

                prefEditor.putLong("UPDATE_DATE_" + String.join(",", dateList), Calendar.getInstance().getTimeInMillis());
                prefEditor.apply();
                prefEditor.commit();

                return true;
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(result -> {
                ((SwipeRefreshLayout) findViewById(R.id.swipeContainer)).setRefreshing(false);
            }, throwable -> {
                ((SwipeRefreshLayout) findViewById(R.id.swipeContainer)).setRefreshing(false);
                showErrorDialog(throwable.getMessage());
            });

        } catch (Exception e) {
            ((SwipeRefreshLayout) findViewById(R.id.swipeContainer)).setRefreshing(false);
            showErrorDialog(e.getMessage());
        }
    }

    private void requestStudentProfile() {
        ((SwipeRefreshLayout) findViewById(R.id.swipeContainer)).setRefreshing(true);

        ApiService apiServiceDnevnik = RetrofitClient.getDnevnikClient().create(ApiService.class);

        Call<List<StudentProfile>> call = apiServiceDnevnik.getStudentProfiles(token);
        call.enqueue(new Callback<List<StudentProfile>>() {
            @Override
            public void onResponse(Call<List<StudentProfile>> call, Response<List<StudentProfile>> response) {
                if (response.isSuccessful()) {
                    List<StudentProfile> studentProfiles = response.body();

                    StudentProfile studentProfile = studentProfiles.stream().findFirst().orElse(null);

                    if (studentProfile != null) {
                        prefEditor.putInt("STUDENT_ID", studentProfile.getId());
                        prefEditor.putString("STUDENT_NAME", studentProfile.getShortName());
                        prefEditor.putString("GROUP_NAME", studentProfile.getClassUnit().getName());
                        prefEditor.apply();
                        prefEditor.commit();

                        initHeader();

                        setWeek();
                    }
                } else {
                    ((SwipeRefreshLayout) findViewById(R.id.swipeContainer)).setRefreshing(false);

                    try {
                        JsonObject jsonObject = JsonParser.parseString(response.errorBody().string()).getAsJsonObject();
                        String errorMessage = jsonObject.get("message").getAsString();
                        if (Objects.equals(errorMessage, "Предыдущая сессия работы в ЭЖД завершена. Войдите в ЭЖД заново")) {
                            showTokenErrorDialog();
                        } else {
                            showErrorDialog(errorMessage);
                        }

                    } catch (Exception e) {
                        try {
                            showErrorDialog(e.getMessage() + "\n\n" + response.errorBody().string());
                        } catch (Exception ex) {
                            showErrorDialog(ex.getMessage());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<StudentProfile>> call, Throwable t) {
                ((SwipeRefreshLayout) findViewById(R.id.swipeContainer)).setRefreshing(false);

                showErrorDialog(t.getMessage());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuSettings) {
            showTokenDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showTokenDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog);

        builder.setTitle("Авторизация");

        String message = "\n1. Войдите в свой аккаунт на сайте МЭШ https://school.mos.ru" +
                "\n\n2. Перейдите по данной ссылке и скопируйте оттуда токен https://school.mos.ru/v2/token/refresh" +
                "\n\n3. Введите полученный токен:\n";

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(message);
        Linkify.addLinks(spannableStringBuilder, Linkify.WEB_URLS);
        builder.setMessage(spannableStringBuilder);

        final EditText input = new EditText(context);
        input.setTextSize(12);
        builder.setView(input);

        builder.setPositiveButton("Применить", (dialog, which) -> {
            String s = input.getText().toString();
            if (s.isEmpty()) {
                showErrorDialog("Токен не может быть пустой");
                return;
            }
            token = s;
            prefEditor.clear();
            prefEditor.apply();
            prefEditor.commit();
            prefEditor.putString("TOKEN", token);
            prefEditor.apply();
            prefEditor.commit();

            requestStudentProfile();
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        ((TextView) dialog.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void showTokenErrorDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog);

        builder.setTitle("Токен истек");

        String message = "Срок действия токена истек, требуется повторная авторизация";
        builder.setMessage(message);

        builder.setPositiveButton("Авторизоваться", (dialog, which) -> {
            showTokenDialog();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showErrorDialog(String errorText) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog);

        builder.setTitle("Ошибка");

        builder.setMessage(errorText);

        builder.setPositiveButton("Ок", (dialog, which) -> {
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void initHeader() {
        studentId = settings.getInt("STUDENT_ID", -1);
        studentName = settings.getString("STUDENT_NAME", "");
        studentGroup = settings.getString("GROUP_NAME", "");

        setTitle(studentGroup + " " + studentName);
    }

//    public void createTimetablePages() {
//        pager = findViewById(R.id.pager);
//        FragmentStateAdapter pageAdapter = new MyAdapter(this);
//        pager.setAdapter(pageAdapter);
//
//        tabLayout = findViewById(R.id.tab_layout);
//        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(TabLayout.Tab tab, int position) {
//                tab.setText(TABS_NAMES[position]);
//            }
//        });
//        tabLayoutMediator.attach();
//
//        pager.setCurrentItem(TABS[changeDayOfWeek], false);
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menuChangeGroup:
//                if (changeGroup == 1) {
//                    changeGroup = 2;
//                } else {
//                    changeGroup = 1;
//                }
//                setTitle("12ИС / " + changeGroup + " подгруппа");
//                setTimetable();
//                return true;
//
//
//            case R.id.menuSettings:
////                Intent intent = new Intent(this, SettingsActivity.class);
////                startActivity(intent);
////
////                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
////                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
////                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        prefEditor.putInt("changeGroup", changeGroup);
//        prefEditor.apply();
//    }
//
//    public void updCurDateVar() {
//        if (curCalendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0) {
//            curWeekType = 2;
//        } else {
//            curWeekType = 1;
//        }
//
//        curWeekNum = curCalendar.get(Calendar.WEEK_OF_YEAR);
//        curDayOfWeek = DAYS_OF_WEEK[curCalendar.get(Calendar.DAY_OF_WEEK) - 1];
//    }
//
//    public void updChangeDateVar() {
//        changeDayOfWeek = DAYS_OF_WEEK[changeCalendar.get(Calendar.DAY_OF_WEEK) - 1];
//
//        if (changeCalendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0) {
//            changeWeekType = 2;
//        } else {
//            changeWeekType = 1;
//        }
//
//        changeWeekNum = changeCalendar.get(Calendar.WEEK_OF_YEAR);
//
//        changeWeekDays = new Integer[7];
//        changeWeekMonth = new Integer[7];
//        for (int d = 0; d < 7; d++) {
//            changeCalendar.set(Calendar.DAY_OF_WEEK, d + 2);
//            changeWeekDays[d] = changeCalendar.get(Calendar.DAY_OF_MONTH);
//            changeWeekMonth[d] = changeCalendar.get(Calendar.MONTH);
//        }
//    }
//
//    // получение времени начала/конца пары из строки
//    public MyTime[] splitCallsString(String str) {
//        str = str.replace("\"", "").replace("\r", "").replace("\n", "");
//
//        String[] strParts = str.split("-");
//
//        MyTime[] calls = new MyTime[2];
//
//        calls[0] = new MyTime(
//                Integer.parseInt(strParts[0].split("\\.")[0]),
//                Integer.parseInt(strParts[0].split("\\.")[1])
//        );
//
//        calls[1] = new MyTime(
//                Integer.parseInt(strParts[1].split("\\.")[0]),
//                Integer.parseInt(strParts[1].split("\\.")[1])
//        );
//
//        return calls;
//    }
//
//    // получение расписания звонков из файла
//    public void getCalls() {
//        try {
//            InputStream inputStream = getResources().openRawResource(R.raw.calls_1mok);
//
//            byte[] buffer = new byte[inputStream.available()];
//            inputStream.read(buffer);
//            inputStream.close();
//            String text = new String(buffer).replace("\r", "");
//
//            String[] callsBlocks = text.split("========\n");
//
//            String[] Monday = callsBlocks[0].split("\n");
//            String[] Other = callsBlocks[1].split("\n");
//
//            // структура    номер дня недели; номер пары; время начала или конца;
//            callsSchedule = new MyTime[5][5][2];
//
//            for (int i = 0; i < Monday.length; i++) {
//                callsSchedule[0][i] = splitCallsString(Monday[i]);
//            }
//            for (int i = 0; i < Other.length; i++) {
//                callsSchedule[1][i] = splitCallsString(Other[i]);
//                callsSchedule[2][i] = splitCallsString(Other[i]);
//                callsSchedule[3][i] = splitCallsString(Other[i]);
//                callsSchedule[4][i] = splitCallsString(Other[i]);
//            }
//
//        } catch (Exception ex) {
//            Log.e(LOG_TAG, Log.getStackTraceString(ex));
//        }
//    }
//
//    // получение расписания из файла
//    public void getTimetable() {
//        try {
//            InputStream inputStream = getResources().openRawResource(R.raw.timetable_1mok);
//
//            byte[] buffer = new byte[inputStream.available()];
//            inputStream.read(buffer);
//            inputStream.close();
//            String text = new String(buffer).replace("\r", "");
//
//            String[] timetableBlocks = text.split("========\n");
//
//            // структура    номер дня недели; группа; тип недели (верхняя / нижняя); какая пара по счету
//            timetable = new Pair[5][2][2][5];
//
//            // цикл по дням неледи
//            for (int i = 0; i < timetableBlocks.length; i++) {
//                String[] string = timetableBlocks[i].split("\n");
//                // цикл по блокам занятий
//                for (int j = 0; j < string.length; j = j + 6) {
//                    String[] groupAndWeekList = string[j].split(" ");
//                    // цикл по вариантам группа/неделя
//                    for (int k = 0; k < groupAndWeekList.length; k++) {
//                        int g = Integer.parseInt(groupAndWeekList[k].split("")[0]) - 1;
//                        int w = Integer.parseInt(groupAndWeekList[k].split("")[1]) - 1;
//                        int p = Integer.parseInt(string[j + 1]) - 1;
//
//                        timetable[i][g][w][p] = new Pair(
//                                string[j + 2],
//                                string[j + 3],
//                                string[j + 4],
//                                Integer.parseInt(string[j + 5]),
//                                callsSchedule[i][p][0],
//                                callsSchedule[i][p][1]);
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            Log.e(LOG_TAG, Log.getStackTraceString(ex));
//        }
//    }

//    // создание вкладок с расписанием
//    public void createTimetablePages() {
//        pager = findViewById(R.id.pager);
//        FragmentStateAdapter pageAdapter = new MyAdapter(this);
//        pager.setAdapter(pageAdapter);
//
//        tabLayout = findViewById(R.id.tab_layout);
//        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(TabLayout.Tab tab, int position) {
//                tab.setText(TABS_NAMES[position] + "\n" + changeWeekDays[position] + " " + MONTHS_NAMES[changeWeekMonth[position]]);
//            }
//        });
//        tabLayoutMediator.attach();
//
//        pager.setCurrentItem(TABS[changeDayOfWeek], false);
//    }

//    public void setTimetable() {
//        EventBus.getDefault().postSticky(new SetTimetableEvent(
//                timetable,
//                changeGroup,
//                changeWeekType,
//                changeWeekNum,
//                lastHighlightWeek,
//                lastHighlightDay,
//                lastHighlightPair,
//                lastHighlightBreak,
//                brakeBlockLabel));
//    }
//
//    // обновление заголовков вкладок
//    public void updateTabsNames() {
//        for (int i = 0; i < 5; i++) {
//            tabLayout.getTabAt(i).setText(TABS_NAMES[i] + "\n" + changeWeekDays[i] + " " + MONTHS_NAMES[changeWeekMonth[i]]);
//        }
//    }
//
//    MyTime curTime;
//    Handler handlerHighlight = new Handler();
//    Runnable runnableHighlight = new Runnable() {
//        @Override
//        public void run() {
//            try {
//                curCalendar = Calendar.getInstance();
//                updCurDateVar();
//
//                curTime = new MyTime(
//                        curCalendar.get(Calendar.HOUR_OF_DAY),
//                        curCalendar.get(Calendar.MINUTE),
//                        curCalendar.get(Calendar.SECOND)
//                );
//
//                label1:
//                {
//                    int dayOfWeek = curDayOfWeek;
//                    int dayDelta = 0;
//                    int weekType = curWeekType;
//                    int weekNum = curWeekNum;
//                    while (true) {
//                        if (dayOfWeek > 4) {
//                            dayDelta += (7 - dayOfWeek);
//                            dayOfWeek = 0;
//
//                            if (weekType == 1) {
//                                weekType = 2;
//                            } else {
//                                weekType = 1;
//                            }
//                            weekNum += 1;
//                        }
//                        for (int pair = 0; pair < 5; pair++) {
//                            if (timetable[dayOfWeek][changeGroup - 1][weekType - 1][pair] != null) {
//                                if (MyTime.subtractionTimesSecond(
//                                        MyTime.additionTimes(
//                                                timetable[dayOfWeek][changeGroup - 1][weekType - 1][pair].getTimeStart(),
//                                                new MyTime(dayDelta, 0, 0, 0
//                                                )), curTime) > 0) {
//                                    String newBrakeBlockLabel = "Сейчас занятий нет";
//                                    if (pair > 0 && timetable[dayOfWeek][changeGroup - 1][weekType - 1][pair - 1] != null) {
//                                        newBrakeBlockLabel = "Перерыв " + MyTime.subtractionTimes(
//                                                timetable[dayOfWeek][changeGroup - 1][weekType - 1][pair].getTimeStart(),
//                                                timetable[dayOfWeek][changeGroup - 1][weekType - 1][pair - 1].getTimeEnd()
//                                        ).getMinute() + " минут";
//                                    }
//
//                                    if (brakeBlockLabel != newBrakeBlockLabel || lastHighlightWeek != weekNum || lastHighlightDay != dayOfWeek || lastHighlightPair != -1 || lastHighlightBreak != pair) {
//                                        brakeBlockLabel = newBrakeBlockLabel;
//                                        lastHighlightWeek = weekNum;
//                                        lastHighlightDay = dayOfWeek;
//                                        lastHighlightPair = -1;
//                                        lastHighlightBreak = pair;
//
//                                        setTimetable();
//                                    }
//                                    timerText = "До начала пары:\n" + MyTime.doTimesFormatedString(MyTime.subtractionTimes(
//                                            MyTime.additionTimes(
//                                                    timetable[dayOfWeek][changeGroup - 1][weekType - 1][pair].getTimeStart(),
//                                                    new MyTime(dayDelta, 0, 0, 0)),
//                                            curTime));
//
//                                    break label1;
//                                }
//                                if (MyTime.subtractionTimesSecond(
//                                        MyTime.additionTimes(
//                                                timetable[dayOfWeek][changeGroup - 1][weekType - 1][pair].getTimeEnd(),
//                                                new MyTime(dayDelta, 0, 0, 0
//                                                )), curTime) > 0) {
//                                    if (lastHighlightWeek != weekNum || lastHighlightDay != dayOfWeek || lastHighlightPair != pair || lastHighlightBreak != -1) {
//                                        lastHighlightWeek = weekNum;
//                                        lastHighlightDay = dayOfWeek;
//                                        lastHighlightPair = pair;
//                                        lastHighlightBreak = -1;
//
//                                        setTimetable();
//                                    }
//
//                                    timerText = "До конца пары:\n" + MyTime.doTimesFormatedString(MyTime.subtractionTimes(
//                                            MyTime.additionTimes(
//                                                    timetable[dayOfWeek][changeGroup - 1][weekType - 1][pair].getTimeEnd(),
//                                                    new MyTime(dayDelta, 0, 0, 0)),
//                                            curTime));
//
//                                    break label1;
//                                }
//                            }
//                        }
//                        dayOfWeek += 1;
//                        dayDelta += 1;
//                    }
//                }
//
//                changeTab = pager.getCurrentItem();
//                if ((curDayOfWeek < 5 && curWeekNum != changeWeekNum) || (curDayOfWeek > 4 && curWeekNum + 1 != changeWeekNum) || TABS[curDayOfWeek] != changeTab) {
//                    buttonToCurrentDate.setEnabled(true);
//                } else {
//                    buttonToCurrentDate.setEnabled(false);
//                }
//
//                highlight();
//            } catch (Exception ex) {
//                Log.e(LOG_TAG, Log.getStackTraceString(ex));
//            }
//        }
//    };
//
//    public void highlight() {
//        handlerHighlight.postDelayed(runnableHighlight, 10);
//    }
//
//    Handler handlerTimer = new Handler();
//    Runnable runnableTimer = new Runnable() {
//        @Override
//        public void run() {
//            try {
//                textViewTimer.setText(timerText);
//
//                timer();
//            } catch (Exception ex) {
//                Log.e(LOG_TAG, Log.getStackTraceString(ex));
//            }
//        }
//    };
//
//    public void timer() {
//        handlerTimer.postDelayed(runnableTimer, 250);
//    }
//
//
//    // Обработка нажатий кнопок
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.buttonWeekUp:
//                changeCalendar.add(Calendar.DAY_OF_YEAR, 7);
//                updChangeDateVar();
//                updateTabsNames();
//                setTimetable();
//                break;
//
//            case R.id.buttonWeekDown:
//                changeCalendar.add(Calendar.DAY_OF_YEAR, -7);
//                updChangeDateVar();
//                updateTabsNames();
//                setTimetable();
//                break;
//
//            case R.id.buttonToCurrentDate:
//                changeCalendar = Calendar.getInstance();
//                if (DAYS_OF_WEEK[changeCalendar.get(Calendar.DAY_OF_WEEK) - 1] > 4) {
//                    changeCalendar.add(Calendar.DAY_OF_YEAR, 7);
//                }
//                updChangeDateVar();
//                updateTabsNames();
//                pager.setCurrentItem(TABS[curDayOfWeek], true);
//                setTimetable();
//                break;
//        }
//    }
}