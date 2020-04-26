# About
A utility transforms the flat data into hierarchical data and display the result.  

# Requirement
Below is employee data of a small company.
It represents the hierarchical relationship among employees. CEO of the company doesn't
have a manager.

| Employee Name | id | Manager id|
| --------------| :--------: | -----:|
| Alan          | 100        | 150   |
| Martin        | 220        | 100   |
| Jamie         | 150        |       |
| Alex          | 275        | 150   |
| Steve         | 400        | 150   |
| David         | 190        | 400   |

Design a suitable representation of this data. Feel free to choose any database (RDBMS, in-
memory database etc), file system or even a data structure like List or Map. Then write code
(in any language and framework) that displays the organisation hierarchy as below:

|Jamie |        |        |
| ---- | :----: | ----:  |
|      | Alan   |        |
|      |        | Martin |
|      |        | Alex   |
|      | Steve  |        |
|      |        | David  |

# Assumption
The company only has one CEO. 

# Future Improvement
Recursive function can have impact on performance when the hierarchy is complicated, 
potentially the loop iteration or streaming can be used to go through the csv file.

# Instructions
   1. Requires Java 1.8+ & Gradle 4.9 
   2. Format the organisation csv data with the format "Employee Name,id,Manager id".  

# Compile & Run
The project can be compiled with command:
    
    gradle clean install

The company data is copied to a CSV file, the first line of the csv should be the header of each 
field. 

The run command:

    java -jar organisationLoader.jar -f [path to CSV file]


