package com.wilton.spark.workflow;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

public class GroupTop3 {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf()
                .setAppName("GroupTop3")
                .setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("C://Users//Administrator//Desktop//scores.txt");

        JavaPairRDD<String, Integer> pairLines = lines.mapToPair(new PairFunction<String, String, Integer>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Tuple2<String, Integer> call(String line) throws Exception {
                String[] lineSplit = line.split(" ");
                return new Tuple2<String, Integer>(lineSplit[0], Integer.valueOf(lineSplit[1]));
            }
        });

        JavaPairRDD<String, Iterable<Integer>> grouplines = pairLines.groupByKey();

        JavaPairRDD<String, Iterable<Integer>> groupTop3 = grouplines.mapToPair(new PairFunction<Tuple2<String, Iterable<Integer>>, String, Iterable<Integer>>() {
            @Override
            public Tuple2<String, Iterable<Integer>> call(Tuple2<String, Iterable<Integer>> line) throws Exception {
                Integer[] top3 = new Integer[3];
                String className = line._1;
                Iterator<Integer> iterator = line._2.iterator();
                while (iterator.hasNext()) {
                    Integer score = iterator.next();
                    for (int i = 0; i < 3; i++) {
                        if (top3[i] == null) {
                            top3[i] = score;
                            break;
                        }else if (score > top3[i]) {
                            for (int j = 2; j > i; j--) {
                                top3[j] = top3[j-1];
                            }
                            top3[i] = score;
                            break;
                        }
                    }
                }
                return new Tuple2<String,Iterable<Integer>>(className, Arrays.asList(top3));
            }
        });

        groupTop3.foreach(new VoidFunction<Tuple2<String, Iterable<Integer>>>() {
            @Override
            public void call(Tuple2<String, Iterable<Integer>> s) throws Exception {
                System.out.println(s);
                System.out.println(s._1);
                Iterator<Integer> iterator = s._2.iterator();
                while(iterator.hasNext()){
                    System.out.println(iterator.next());
                }
                System.out.println("===================================");
            }
        });

        sc.stop();

    }


}
