package com.lbibera.hadoop.designpatterns;

import com.lbibera.hadoop.designpatterns.average.App;
import org.apache.hadoop.util.ProgramDriver;

/**
 * entry point of all the Jobs
 * copied from: https://github.com/apache/hadoop/blob/trunk/hadoop-mapreduce-project/hadoop-mapreduce-examples/src/main/java/org/apache/hadoop/examples/ExampleDriver.java
 */
public class MainDriver {

    public static void main(String... args) {
        ProgramDriver driver = new ProgramDriver();

        int exitCode = -1;
        try {
            driver.addClass("average", App.class, "Calculates the Average");

            exitCode = driver.run(args);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        System.exit(exitCode);
    }
}
