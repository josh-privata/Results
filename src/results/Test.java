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
package results;

import java.sql.SQLException;
import java.sql.Statement;
import model.ResultsQueries;

/**
 *
 * @author josh
 */
public class Test {

    // Global Variables
    private ResultsQueries rq;
    private Statement statement;
    private final String droptable = "DROP TABLE MARKS";
    private final String createtable = "CREATE TABLE MARKS ("
            + "STUDENTID VARCHAR (8) NOT NULL,"
            + "ASSIGNMENT1 INT NOT NULL,"
            + "ASSIGNMENT2 INT NOT NULL,"
            + "EXAM INT NOT NULL,"
            + "TOTAL INT NOT NULL,"
            + "GRADE VARCHAR (4) NOT NULL,"
            + "PRIMARY KEY (STUDENTID))";
    private final String insertdata = "INSERT INTO MARKS"
            + "(STUDENTID,ASSIGNMENT1,ASSIGNMENT2,EXAM,TOTAL,GRADE)"
            + "VALUES"
            + "('S01',20,0,25,45,'?'),"
            + "('S02',0,0,0,0,'?'),"
            + "('S03',15,0,0,15,'?'),"
            + "('S04',15,15,19,49,'?'),"
            + "('S05',25,20,39,84,'?'),"
            + "('S06',0,0,45,45,'?'), "
            + "('S07',25,25,0,50,'?'),"
            + "('S08',20,20,25,65,'?'),"
            + "('S09',20,20,24,64,'?'),"
            + "('S10',17,19,39,75,'?')";

    // Constructor initiates ResultsQueries object, creates table and fills with data
    public Test() {
        System.out.println("[+] Initialising the Marks Management System Test\n");
        try {
            System.out.println("[+] TESTING CONNECTION");
            rq = new ResultsQueries();
            System.out.println("[+] Preparing to create the data table and fill it with data");
            statement = rq.con.createStatement();
            try {
                statement.executeUpdate(droptable);
            } catch (SQLException e) {
                System.out.println("[-] Existing table was not found");
            }
            System.out.println("[+] Existing table has been removed");
            statement.executeUpdate(createtable);
            System.out.println("[+] New table created successfully");
            statement.executeUpdate(insertdata);
            System.out.println("[+] Test data entered successfully");
            System.out.println("[+] CONNECTION PASSED");
        } catch (SQLException ex) {
            System.out.println("[-] The table has not been created");
            System.out.println("[-] CONNECTION FAILED");
        }
    }

    // Runs queries in ResultsQueries class with set parameters
    public void runTest() {
        try {
            System.out.println("\n[+] TESTING METHOD - getNumberOfAbsentFails");
            rq.getNumberOfAbsentFails();
            System.out.println("[+] METHOD PASSED");
        } catch (Exception ex) {
            System.out.println("[-] METHOD FAILED");
        } 

        try {
            System.out.println("\n[+] TESTING METHOD - getResultsForAllStudents");
            rq.getResultsForAllStudents();
            System.out.println("[+] METHOD PASSED");           
        } catch (Exception ex) {
            System.out.println("[-] METHOD FAILED");
        } 

        try {
            System.out.println("\n[+] TESTING METHOD - getTotalMarksInRange");
            rq.getTotalMarksInRange(50, 70);
            System.out.println("[+] METHOD PASSED");
        } catch (Exception ex) {
            System.out.println("[-] METHOD FAILED");
        } 

        try {
            System.out.println("\n[+] TESTING METHOD - getResultsForStudent");
            rq.getResultsForStudent("S02");
            System.out.println("[+] METHOD PASSED");
        } catch (Exception ex) {
            System.out.println("[-] METHOD FAILED");
        }

        try {
            System.out.println("\n[+] TESTING METHOD - updateExamMark");
            rq.updateExamMark("S02", 12, 34);
            System.out.println("[+] METHOD PASSED");
        } catch (Exception ex) {
            System.out.println("[-] METHOD FAILED");
        } 

        try {
            System.out.println("\n[+] TESTING METHOD - getResultsForStudent");
            rq.getResultsForStudent("S02");
            System.out.println("[+] METHOD PASSED");
        } catch (Exception ex) {
            System.out.println("[-] METHOD FAILED");
        } finally {
            System.out.println("[+] PROGRAM PASSED");
        }
    }
}