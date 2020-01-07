/**
 * Indegree with Queue solution - O(n) time and space
 * 
 * Source: https://leetcode.com/articles/course-schedule-ii/
 */

class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> preCourseToCourses = new HashMap<>();
        int[] indegree = new int[numCourses];
        int[] topologicalOrder = new int[numCourses];

        for (int i = 0; i < prerequisites.length; i++) {
            int targetCourse = prerequisites[i][0];
            int preCourse = prerequisites[i][1];

            List<Integer> coursesPrerequisiteByPrecourse = preCourseToCourses.getOrDefault(preCourse,
                    new ArrayList<>());

            coursesPrerequisiteByPrecourse.add(targetCourse);
            preCourseToCourses.put(preCourse, coursesPrerequisiteByPrecourse);

            indegree[targetCourse]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                queue.add(i);
        }

        int courseIndex = 0;

        while (!queue.isEmpty()) {
            int curCourse = queue.remove();
            topologicalOrder[courseIndex] = curCourse;
            courseIndex++;

            if (preCourseToCourses.containsKey(curCourse)) {
                for (Integer course : preCourseToCourses.get(curCourse)) {
                    indegree[course]--;

                    if (indegree[course] == 0)
                        queue.add(course);
                }
            }
        }

        if (courseIndex == numCourses)
            return topologicalOrder;
        return new int[0];
    }
}

/**
 * OO & recursive solution - O(n) time & O (n) space Source:
 * https://leetcode.com/problems/course-schedule-ii/discuss/59317/Two-AC-solution-in-Java-using-BFS-and-DFS-with-explanation/60628
 */

class Solution {

    private int courseIndex = 0;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] courseSchedule = new int[numCourses];
        Course[] courses = new Course[numCourses];

        for (int courseNum = 0; courseNum < numCourses; courseNum++)
            courses[courseNum] = new Course(courseNum);

        for (int prereqPairIndex = 0; prereqPairIndex < prerequisites.length; prereqPairIndex++) {
            int targetCourse = prerequisites[prereqPairIndex][0];
            int prereqCourse = prerequisites[prereqPairIndex][1];

            courses[targetCourse].add(courses[prereqCourse]);
        }

        for (int courseNum = 0; courseNum < numCourses; courseNum++) {
            if (isCyclic(courses[courseNum], courseSchedule))
                return new int[0];
        }
        return courseSchedule;
    }

    private boolean isCyclic(Course course, int[] courseSchedule) {
        if (course.isNotCyclic)
            return false;
        if (course.completedPreviously)
            return true;

        course.completedPreviously = true;

        for (Course c : course.prerequisite) {
            if (isCyclic(c, courseSchedule))
                return true;
        }

        course.isNotCyclic = true;
        courseSchedule[courseIndex] = course.number;
        courseIndex++;
        return false;
    }

    class Course {
        boolean isNotCyclic = false;
        boolean completedPreviously = false;
        int number;
        List<Course> prerequisite = new ArrayList<>();

        public Course(int courseNum) {
            number = courseNum;
        }

        public void add(Course c) {
            prerequisite.add(c);
        }
    }
}