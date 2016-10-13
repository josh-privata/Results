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

/**
 *
 * @author josh
 */
public class Result {

    // Global Variables
    private int assignment1;
    private int assignment2;
    private int exam;
    private int total;
    private String grade;
    private String student;

    // Contructor without parameters
    public Result() {
    }

    // Constructor with parameters
    public Result(int assignment1, int assignment2, int exam, int total, String grade, String student) {
        this.assignment1 = assignment1;
        this.assignment2 = assignment2;
        this.exam = exam;
        this.total = total;
        this.grade = grade;
        this.student = student;
    }

    // Getters and Setters
    public int getAssignment1() {
        return assignment1;
    }

    public void setAssignment1(int assignment1) {
        this.assignment1 = assignment1;
    }

    public int getAssignment2() {
        return assignment2;
    }

    public void setAssignment2(int assignment2) {
        this.assignment2 = assignment2;
    }

    public int getExam() {
        return exam;
    }

    public void setExam(int exam) {
        this.exam = exam;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    @Override
    public String toString() {
        String s;
        s = String.format("%s\t%s\t%s\t%s\t%s\t%s\n",
                getStudent(), String.valueOf(getAssignment1()), String.valueOf(getAssignment2()),
                String.valueOf(getExam()), String.valueOf(getTotal()), getGrade());

        return s;
    }
}