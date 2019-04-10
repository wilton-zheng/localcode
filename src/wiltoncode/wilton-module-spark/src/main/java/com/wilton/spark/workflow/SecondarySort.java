package com.wilton.spark.workflow;

import com.wilton.spark.common.utils.SecondarySortKey;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

public class SecondarySort {
    public static void main(String args[]){
        SparkConf conf = new SparkConf()
                .setAppName("SecondarySort")
                .setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("C://Users//Administrator//Desktop//sort.txt");

        JavaPairRDD<SecondarySortKey,String> pairs = lines.mapToPair(new PairFunction<String, SecondarySortKey,String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Tuple2<SecondarySortKey, String> call(String line) throws Exception {
                String[] lineSplit = line.split(" ");
                SecondarySortKey key = new SecondarySortKey(
                        Integer.valueOf(lineSplit[0]),
                        Integer.valueOf(lineSplit[1])
                );
                return new Tuple2<SecondarySortKey, String>(key,line);
            }
        });

        JavaPairRDD<SecondarySortKey,String> sortPairs = pairs.sortByKey();

        JavaRDD<String> sortedLines = sortPairs.map(new Function<Tuple2<SecondarySortKey, String>, String>() {
            private static final long serialVersionUID = 1L;
            @Override
            public String call(Tuple2<SecondarySortKey, String> v1) throws Exception {
                return v1._2;
            }
        });

        sortedLines.foreach(new VoidFunction<String>() {
            private static final long serialVersionUID = 1L;
            @Override
            public void call(String s) throws Exception {
                System.out.println(s);
            }
        });

        sc.stop();
    }
}
