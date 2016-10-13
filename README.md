<h1 align="center">
  <a href="http://java.com/en"><img src="https://cloud.githubusercontent.com/assets/5771200/19331298/6f964780-9127-11e6-88bd-55ac19e1ad12.jpg" alt="Java" height="150"></a>
  <br>
  <br>
  Results Manager
  <br>
  <br>
</h1>
<h4 align="center">A simple java program to manage students school results</h4>

<p align="center">
  <a href=""><img src="https://img.shields.io/travis/feross/standard/master.svg" alt="Passing"></a>
  <a href="https://java.com/en/"><img src="https://img.shields.io/badge/Java-1.8.0__101-brightgreen.svg" alt="Java 1.8.0"></a>
  <a href="https://opensource.org/licenses/BSD-2-Clause"><img src="https://img.shields.io/badge/License-BSD-blue.svg" alt="BSD License"></a>
</p>
<br>

## Table of Contents
- [Synopsis](#synopsis)
- [Install](#install)
- [Usage](#usage)
- [Screenshots](#screenshots)
- [License](#license)
## Synopsis
The problem put forward was to design a program to assist a course coordinator in the management of student results. A Java desktop application is to be developed, driven by a simple Swing GUI. Interaction between the database and the application is to be via JDBC. The NetBeans IDE is to be used for development. Derby must be used as the database connector.

The initial user requirements are:

1. Start the application and connect to the database. If a connection cannot be established, the application must exit.
2. Close the database connection and stop the application
3. Display all records 
4. Display the record for a specified student
5. Display all records where the total mark is within a specified range
6. Display the number of students who have received 0 for all assessment items (i.e. absent fails)
7. Update the exam mark and total mark for a specified student. 

## Install
First, make a directory to install the files to and change to that directory using :
```bash
 mkdir results && cd results
```
Then all you need to do is clone the project from github into the directory by using :
```bash
 git clone https://github.com/josh-privata/Results.git
```
## Usage
##### Note:  [Java Runtime](https://java.com/en/download/) is required to run the preceding commands.
Initially the program needs to be compiled. After you have copied the *.java files to a directory, run the command :
```bash
$ javac *.java
```
Then run the program using the command :
```bash
$ java Results
```
## Screenshots
<p align="center"><img src="https://cloud.githubusercontent.com/assets/5771200/19331432/2f979372-9128-11e6-8df0-e032f4ed2ade.png" alt="Screenshot"></p>
<p align="center"><img src="https://cloud.githubusercontent.com/assets/5771200/19331433/2fc27c36-9128-11e6-9639-e6c38189fdf0.png" alt="Screenshot"></p>
<p align="center"><img src="https://cloud.githubusercontent.com/assets/5771200/19331395/0d0fd2f6-9128-11e6-84c4-f23792d41fa4.png" alt="Screenshot"></p>
<p align="center"><img src="https://cloud.githubusercontent.com/assets/5771200/19331393/0d0d9220-9128-11e6-8bc0-3e6897b3d4ab.png" alt="Screenshot"></p>

## License
[BSD](LICENSE) Copyright (c) 2016 [Josh Cannons](http://joshcannons.com).