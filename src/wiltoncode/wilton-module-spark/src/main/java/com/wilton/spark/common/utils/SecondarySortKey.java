package com.wilton.spark.common.utils;

import scala.math.Ordered;

import java.io.Serializable;
import java.util.Objects;

public class SecondarySortKey implements Ordered<SecondarySortKey>,Serializable {
    private static final long serialVersionUID = -2366006422945129991L;
    private int first;
    private int second;


    public SecondarySortKey(int first,int second){
        this.first = first;
        this.second = second;
    }

    public int getFirst(){
        return first;
    }

    public void setFirst(int first){
        this.first = first;
    }

    public int getSecond(){
        return first;
    }

    public void setSecond(int second){
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecondarySortKey that = (SecondarySortKey) o;
        return first == that.first &&
                second == that.second;
    }

    @Override
    public int hashCode() {

        return Objects.hash(first, second);
    }

    @Override
    public int compare(SecondarySortKey that) {
        if(this.first - that.getFirst() != 0){
            return this.first - that.getFirst();
        }else{
            return this.second - that.getSecond();
        }
    }

    @Override
    public boolean $less(SecondarySortKey that) {
        if(this.first < that.first){
            return true;
        } else if(this.first == that.first && this.second < that.second){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean $greater(SecondarySortKey that) {
        if(this.first > that.first){
            return true;
        }else if (this.first == that.first && this.second > that.second){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean $less$eq(SecondarySortKey that) {
        if(this.$less(that)){
            return true;
        }else if (this.first == that.getFirst() && this.second == that.getSecond()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean $greater$eq(SecondarySortKey that) {
        if(this.$greater(that)){
            return true;
        }else if (this.first == that.getFirst() && this.second == that.getSecond()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int compareTo(SecondarySortKey that) {
        if(this.first - that.getFirst() != 0){
            return this.first - that.getFirst();
        }else{
            return this.second - that.getSecond();
        }
    }
}
