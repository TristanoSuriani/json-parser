package nl.suriani.json.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Something {
    private String aString;

    private int anInt;
    private Integer anInteger;
    private long aLong;
    private Long aLongBigL;
    private BigInteger aBigInteger;
    private float aFloat;
    private Float aFloatBigF;
    private double aDouble;
    private Double aDoubleBigD;
    private BigDecimal aBigDecimal;
    private Something something;
    private int[] arrayOfInts;
    public Something() {
    }

    public String getaString() {
        return aString;
    }

    public Something getSomething() {
        return something;
    }

    public int getAnInt() {
        return anInt;
    }

    public Integer getAnInteger() {
        return anInteger;
    }

    public long getaLong() {
        return aLong;
    }

    public Long getaLongBigL() {
        return aLongBigL;
    }

    public BigInteger getaBigInteger() {
        return aBigInteger;
    }

    public float getaFloat() {
        return aFloat;
    }

    public Float getaFloatBigF() {
        return aFloatBigF;
    }

    public double getaDouble() {
        return aDouble;
    }

    public Double getaDoubleBigD() {
        return aDoubleBigD;
    }

    public BigDecimal getaBigDecimal() {
        return aBigDecimal;
    }
}