# LanguageFinder

To check the language of lines in given file:
Usage: java -jar LanguageFinder.jar [input] [output] check 

![alt text](https://i.gyazo.com/a8c86fb5d044e6d34d8bc4da0c5a9a17.png)

## Steps done in order to get a working product:
1) Write a program which is able to count to occurrences of characters and neighbors (1 on each side).
2) Write logic to interpret the generated occurrence counts as odds (hadoop.model.CooccurrenceMap).
3) Write a program which is able to use the CooccurrenceMap to interpret the language of given sentence.

Should also work in Hadoop environment but was unable to confirm due to problem with my installation
which I wasn't able to resolve after many hours of searching for a cause.
  
![alt text](https://i.gyazo.com/087038dea189019b1242040df8273de4.png)
