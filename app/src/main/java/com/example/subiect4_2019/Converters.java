package com.example.subiect4_2019;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converters {
    @TypeConverter
    public static Long fromDate ( Date value){
        return value==null?null:value.getTime();
    }

    @TypeConverter
    public static Date fromLong( Long value ){
        return value==null?null:new Date(value);
    }
}
