package extra;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Statistics {

    public static void main(String[] args) {

        var fileContent = List.of(
                "22 oct - session 1  : 22:00 - 01:00 (3:00)",
                "---------------------------------------------",
                "25 oct - session 2  : 12:50 - 16:05 (3:15)",
                "---------------------------------------------",
                "27 oct - session 3  : 14:00 - 15:20 (1:20)",
                "бл**ь, переделывать часть сегодня сделанной работы.",
                "27 oct - session 4  : 15:30 - 15:50 (0:20)",
                "27 oct - session 5  : 17:00 - 21:05 (4:05)",
                "---------------------------------------------",
                "29 oct - session 6  : 14:00 - 20:00 (6:00)",
                "29 oct - session 7  : 21:30 - 22:45 (1:15)",
                "29 oct - session 8  : 23:00 - 00:15 (1:15)",
                "---------------------------------------------",
                "30 oct - session 9  : 13:20 - 16:10 (2:50)",
                "30 oct - session 10 : 17:40 - 19:50 (2:10)",
                "ура!!! Решил все почти все проблемы с передачей img file.",
                "+ сделал большую часть проекта.",
                "30 oct - session 11 : 21:45 - 00:20 (2:35)",
                "---------------------------------------------",
                "1  nov - session 12 : 11:00 - 13:20 (2:20)",
                "1  nov - session 13 : 14:00 - 15:30 (1:30)",
                "1  nov - session 14 : 17:20 - 18:50 (1:30)",
                "1  nov - session 15 : 19:40 - 22:10 (2:30)",
                "---------------------------------------------",
                "2  nov - session 16 : 13:20 - 14:10 (0:50)",
                "2  nov - session 17 : 14:50 - 19:30 (4:40)",
                "---------------------------------------------"
        );

//        System.out.println(new StatisticsProcessor().process(fileContent));

    }


    static class StatisticsProcessor {

        private static final String LS = System.lineSeparator();


        public String process(List<String> statistics) {
            List<Session> sessions = collectSessions(statistics);
            int[] sessionsRsl = collectSessionsRsl(sessions);


            return "Statistics info:" + LS
                    + "Session count:          " + sessions.size() + LS
                    + "Total length:           " + prettyRsl(sessionsRsl[2]) + LS
                    + "Total work time:        " + prettyRsl(sessionsRsl[0]) + LS
                    + "Total break time:       " + prettyRsl(sessionsRsl[1]) + LS
                    + "### Details ###" + LS
                    + "Total work minutes:     " + sessionsRsl[0] + LS
                    + "Total break minutes:    " + sessionsRsl[1] + LS
                    + "Total sessions minutes: " + sessionsRsl[2] + LS
                    + "### Sessions ###" + LS
                    + getSessionInfo(sessions) + LS;
        }

        public String getSessionInfo(List<Session> sessions) {
            StringJoiner sessionInfo = new StringJoiner(LS);
            for (int i = 0; i < sessions.size(); i++) {
                var current = sessions.get(i);
                sessionInfo
                        .add("Session #" + (i + 1))
                        .add("work time:   " + prettyRsl(current.getWorkMins()))
                        .add("break time:  " + prettyRsl(current.getBreakMins()))
                        .add("total time:  " + prettyRsl(current.getWorkMins() + current.getBreakMins()))
                        .add("=========================");
            }
            return sessionInfo.toString();
        }

        private String prettyRsl(int val) {
            return (val / 60) + "h " + (val % 60) + 'm';
        }

        private List<Session> collectSessions(List<String> statistics) {
            List<Session> sessions = new ArrayList<>();
            Session currentSession = new Session();

            for (String each : statistics) {
                var startWithNum = Character.isDigit(each.charAt(0));

                if (startWithNum) {
                    currentSession.add(new TimePoint(
                            Integer.parseInt(each.substring(22, 24)), // hours (int)
                            Integer.parseInt(each.substring(25, 27))  // mins (int)
                    ));
                    currentSession.add(new TimePoint(
                            Integer.parseInt(each.substring(30, 32)), // hours (int)
                            Integer.parseInt(each.substring(33, 35))  // mins (int)
                    ));
                } else if (each.charAt(0) == '-') {
                    sessions.add(currentSession);
                    currentSession = new Session();
                }
            }
            return sessions;
        }

        /**
         * @param sessions-
         * @return new in[]
         * index meaning:
         * 0 : workMins for all sessions
         * 1 : breakMins for all sessions
         * 2 : totalMins for all sessions
         */
        private int[] collectSessionsRsl(List<Session> sessions) {
            var rsl = new int[]{0, 0, 0};
            for (var each : sessions) {
                var data = each.getResultStatistics();
                rsl[0] += data[0];
                rsl[1] += data[1];
                rsl[2] += data[2];
            }
            return rsl;
        }
    }

    static class Session {
        private final List<TimePoint> timePoints = new ArrayList<>();
        private int workMins = 0;
        private int breakMins = 0;

        public void add(TimePoint timePoint) {
            timePoints.add(timePoint);
        }

        public int[] getResultStatistics() {
            TimePoint before = null;
            boolean isOddTimePoint = false;
            boolean firstTimePointInSession = true; // just add first TimePoint

            for (var current : timePoints) {
                if (firstTimePointInSession) {
                    firstTimePointInSession = false;
                } else {

                    if (isOddTimePoint) {
                        this.breakMins += findPeriodLength(before, current);
                        isOddTimePoint = false;
                    } else {
                        this.workMins += findPeriodLength(before, current);
                        isOddTimePoint = true;
                    }
                }
                before = current;
            }

            return new int[]{
                    this.workMins,
                    this.breakMins,
                    this.workMins + this.breakMins // total mins in session
            };
        }

        public int getWorkMins() {
            return workMins;
        }

        public int getBreakMins() {
            return breakMins;
        }

        private int findPeriodLength(TimePoint beforePoint, TimePoint currentPoint) {
            int rsl;
            int tempBreakMins = currentPoint.getTotalMins() - beforePoint.getTotalMins();
            // if new TimePoint isn't a new real day.
            if (tempBreakMins > 0) {
                rsl = tempBreakMins;
            } else {
                rsl = 24 * 60 - beforePoint.getTotalMins() + currentPoint.getTotalMins();
            }
            return rsl;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Session.class.getSimpleName() + "[", "]")
                    .add("list=" + timePoints)
                    .add("workMins=" + workMins)
                    .add("breakMins=" + breakMins)
                    .toString();
        }
    }


    static class TimePoint {
        final int totalMins;

        public TimePoint(int hours, int mins) {
            this.totalMins = (hours * 60) + mins;
        }

        public int getTotalMins() {
            return totalMins;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", TimePoint.class.getSimpleName() + "[", "]")
                    .add("totalMins=" + totalMins)
                    .toString();
        }
    }
}
