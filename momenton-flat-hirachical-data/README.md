# About
A utility transforms the flat data into hierarchical data and display the result.  

#Requirement
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

#Assumption
The company only has one CEO. 

# Compile & Run

