package com.graph;

import java.util.*;

/**
 * 207. Course Schedule
 * https://leetcode.com/problems/course-schedule/
 */
public class CourseSchedule {
    public static void main(String[] args) {
        CourseSchedule schedule = new CourseSchedule();
        int [][] test1 = {{1,0}, {2,1}};
        System.out.println(schedule.canFinishList(3, test1));

        int [][] test2 = {{0,1}, {1,0}};
        System.out.println(schedule.canFinishList(2, test2));
    }

    //runtime 54ms, memory 49.5mbi
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length == 0) return true;
        Set<Integer>[] nodes = new HashSet[numCourses];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new HashSet<>();
        }
        for(int[] node : prerequisites) {
            nodes[node[0]].add(node[1]);
        }

        Queue<Integer> queue = new LinkedList<>();
        int remainingCourses = numCourses;

        for(int i = 0 ; i < nodes.length ; i++) {
            Set<Integer> node = nodes[i];
            if(node.size() == 0) {
                queue.offer(i);
                remainingCourses--;
            }
        }

        while(!queue.isEmpty()) {
            int val = queue.poll();
            for(int i = 0 ; i < nodes.length ; i++) {
                Set<Integer> node = nodes[i];
                if(node.contains(val)) {
                    node.remove(val);
                    if(node.size() == 0) {
                        queue.offer(i);
                        remainingCourses--;
                    }
                }
            }
        }

        return remainingCourses == 0;
    }

    //Runtime 4ms, memory 45.5MB
    public boolean canFinishList(int numCourses, int[][] prerequisites) {
        if(prerequisites.length == 0) return true;
        //build graph using ArrayList
        List<Integer>[] nodes = new ArrayList[numCourses];
        //maintain array for number of pre-reqs for each course
        int[] degree = new int[numCourses];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new ArrayList<>();
        }
        //build graph
        for(int[] node : prerequisites) {
            nodes[node[0]].add(node[1]);
            //increment pre-req count
            degree[node[1]]++;
        }

        //use queue to keep track of courses without pre-req
        Queue<Integer> queue = new LinkedList<>();
        //keep track of courses that still needs pre-req
        int remainingCourses = numCourses;

        //go through graph and find courses with 0 pre-req
        for(int i = 0 ; i < degree.length ; i++) {
            //if you find course with 0 pre-req add it to queue
            //and decrement count for remaining courses
            if(degree[i] == 0) {
                queue.offer(i);
                remainingCourses--;
            }
        }

        //iterate over queue and start removing dependency
        while(!queue.isEmpty()) {
            int val = queue.poll();
            //iterate over all edges of node with 0 pre-req
            for(int i = 0 ; i < nodes[val].size() ; i++) {
                int node = nodes[val].get(i);
                //decrement pre-req count
                degree[node]--;
                //check if 0, if yes, add to queue
                if(degree[node] == 0) {
                    queue.offer(node);
                    //decrement course count
                    remainingCourses--;
                }
            }
        }

        //if there are any courses left then return false
        return remainingCourses == 0;
    }
}
