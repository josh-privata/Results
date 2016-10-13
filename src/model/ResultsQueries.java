/*
 * Copyright (c) 2016, josh
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author josh
 */
public class ResultsQueries {

    // Public Variables
    public Connection con;

    // Private Variables
    private String service = "jdbc:derby:";
    private String location = "//localhost:1527/";
    private String database = "results";
    private String create = "true";
    private String url = service + location + database + ";create=" + create;
    private String user = "josh";
    private String password = "josh";
    private PreparedStatement selectAbsentFails;
    private PreparedStatement selectAllStudents;
    private PreparedStatement selectStudent;
    private PreparedStatement selectTotalInRange;
    private PreparedStatement selectUpdateExamMark;

    // Constructor creates connection
    public ResultsQueries() {
        this.con = null;
        this.selectAbsentFails = null;
        this.selectAllStudents = null;
        this.selectStudent = null;
        this.selectTotalInRange = null;
        this.selectUpdateExamMark = null;
        try {
            System.out.println("[+] Attempting connecting to database");
            this.con = DriverManager.getConnection(url, user, password);
            if (this.con != null) {
                System.out.println("[+] Connected to database");
            }
        } catch (SQLException ex) {
            System.out.println("[-] Failed to connect to database");
            JOptionPane.showMessageDialog(null, "Cannot connect to database. Please ensure you have started the derby/javaDB service. Program exiting");
            System.exit(1);
        }
    }

    // Query the count of all zero scores
    public int getNumberOfAbsentFails() {
        String query = "SELECT * FROM marks "
                + "     WHERE exam = 0 AND assignment1 = 0 AND assignment2 = 0";
        int fails = 0;
        ResultSet rs = null;
        try {
            System.out.println("[+] Attempting to prepare statement");
            selectAbsentFails = con.prepareStatement(query);
            System.out.println("[+] Attempting to query database for all "
                    + "absent fail instances");
            rs = selectAbsentFails.executeQuery();
            System.out.println("[+] Successful query attempt");
            System.out.println("[+] Appending results");
            while (rs.next()) {
                ++fails;
            }
        } catch (SQLException ex) {
            System.out.println("[-] Query did not execute successfully");
        } finally {
            try {
                System.out.println("[+] Printing results of query");
                System.out.println("[+] Successfully found "
                        + fails + " absent fail instances");
                System.out.println("[+] Attempting to close result set");
                rs.close();
                System.out.println("[+] Result set closed successfuly");
            } catch (SQLException ex) {
                System.out.println("[-] Failed to close result set");
            }
        }
        return fails;
    }

    // Query all students
    public List<Result> getResultsForAllStudents() {
        String query = "SELECT * FROM marks";
        List<Result> results = new LinkedList();
        ResultSet rs = null;
        Result result;
        try {
            selectAllStudents = con.prepareStatement(query);
            System.out.println("[+] Attempting to query database for all students");
            rs = selectAllStudents.executeQuery();
            System.out.println("[+] Successful query attempt");
            System.out.println("[+] Appending List<Result>");
            int i = 0;
            while (rs.next()) {
                result = new Result();
                result.setStudent(rs.getString(1));
                result.setAssignment1(rs.getInt(2));
                result.setAssignment2(rs.getInt(3));
                result.setExam(rs.getInt(4));
                result.setTotal(rs.getInt(5));
                result.setGrade(rs.getString(6));
                results.add(result);
                System.out.println("[+] Successfully added item " + (i + 1));
                ++i;
            }
        } catch (SQLException ex) {
            System.out.println("[-] Query did not execute successfully");
        } finally {
            try {
                System.out.println("[+] Printing results of the query");
                for (Result r : results) {
                    System.out.println(r.toString());
                }
                System.out.println("[+] Attempting to close result set");
                rs.close();
                System.out.println("[+] Result set closed successfuly");
            } catch (SQLException ex) {
                System.out.println("[-] Failed to close result set");
            }
        }
        return results;
    }

    // Query for a particular student
    public Result getResultsForStudent(String student) {
        String query = "SELECT * FROM marks WHERE studentid = ?";
        Result result = new Result();
        ResultSet rs = null;
        try {
            System.out.println("[+] Attempting to prepare statement");
            selectStudent = con.prepareStatement(query);
            selectStudent.setString(1, student);
            System.out.println("[+] Attempting to query database for student "
                    + student);
            rs = selectStudent.executeQuery();
            System.out.println("[+] Successful query attempt");
            System.out.println("[+] Appending Result");
            while (rs.next()) {
                result.setStudent(rs.getString(1));
                result.setAssignment1(rs.getInt(2));
                result.setAssignment2(rs.getInt(3));
                result.setExam(rs.getInt(4));
                result.setTotal(rs.getInt(5));
                result.setGrade(rs.getString(6));
            }
        } catch (SQLException ex) {
            System.out.println("[-] Query did not execute successfully");
        } finally {
            try {
                System.out.println("[+] Printing results of query");
                System.out.println(result.toString());
                System.out.println("[+] Attempting to close result set");
                rs.close();
                System.out.println("[+] Result set closed successfuly");
            } catch (SQLException ex) {
                System.out.println("[-] Failed to close result set");
            }
        }
        return result;
    }

    // Query all students with marks between given number range
    public List<Result> getTotalMarksInRange(int mark1, int mark2) {
        String query = "SELECT * FROM marks WHERE total >= ? AND total <= ?";
        Result result = new Result();
        List<Result> results = new LinkedList();
        ResultSet rs = null;
        try {
            System.out.println("[+] Attempting to prepare statement");
            selectTotalInRange = con.prepareStatement(query);
            selectTotalInRange.setInt(1, mark1);
            selectTotalInRange.setInt(2, mark2);
            System.out.println("[+] Attempting to query database for students"
                    + " within a mark range of " + mark1 + " and " + mark2);
            rs = selectTotalInRange.executeQuery();
            System.out.println("[+] Successful query attempt");
            System.out.println("[+] Appending List<Result>");
            int i = 0;
            while (rs.next()) {
                result = new Result();
                result.setStudent(rs.getString(1));
                result.setAssignment1(rs.getInt(2));
                result.setAssignment2(rs.getInt(3));
                result.setExam(rs.getInt(4));
                result.setTotal(rs.getInt(5));
                result.setGrade(rs.getString(6));
                results.add(result);
                System.out.println("[+] Successfully added item " + (i + 1));
                ++i;
            }
        } catch (SQLException ex) {
            System.out.println("[-] Query did not execute successfully");
        } finally {
            try {
                System.out.println("[+] Printing results of query");
                for (Result r : results) {
                    System.out.println(r.toString());
                }
                System.out.println("[+] Attempting to close result set");
                rs.close();
                System.out.println("[+] Result set closed successfuly");
            } catch (SQLException ex) {
                System.out.println("[-] Failed to close result set");
            }
        }
        return results;
    }

    // Update the exam and total result for a particular student
    public void updateExamMark(String student, int exam, int total) {
        String updateExam = "UPDATE marks SET exam = ? WHERE studentid = ?";
        String updateTotal = "UPDATE marks SET total = ? WHERE studentid = ?";
        try {
            System.out.println("[+] Attempting to update exam results for student " + student);
            selectUpdateExamMark = con.prepareStatement(updateExam);
            System.out.println("[+] Successfully prepared query statement");
            selectUpdateExamMark.setInt(1, exam);
            System.out.println("[+] Successfully inserted exam result variable");
            selectUpdateExamMark.setString(2, student);
            System.out.println("[+] Attempting to execute update");
            selectUpdateExamMark.executeUpdate();
            System.out.println("[+] Successfully executed exam update");
            System.out.println("[+] Attempting to update total results for student " + student);
            selectUpdateExamMark = con.prepareStatement(updateTotal);
            System.out.println("[+] Successfully prepared query statement");
            selectUpdateExamMark.setInt(1, total);
            System.out.println("[+] Successfully inserted total result variable");
            selectUpdateExamMark.setString(2, student);
            System.out.println("[+] Attempting to execute update");
            selectUpdateExamMark.executeUpdate();
            System.out.println("[+] Successfully executed total update");
            System.out.println("[+] Attempting to execute update");
            selectUpdateExamMark.executeUpdate();
            System.out.println("[+] Successfully executed results update");
            con.commit();
            System.out.println("[+] Successfully commited new results for student "
                    + student + " to the database");
        } catch (SQLException ex) {
            System.out.println("[-] Failed to update database");
        }
    }
}