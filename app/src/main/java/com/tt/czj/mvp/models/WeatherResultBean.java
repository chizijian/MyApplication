package com.tt.czj.mvp.models;

/**
 * The type Weather result bean.
 *
 * @Author czj
 * @Time 2016 /3/10
 */
public class WeatherResultBean {


    private int errNum;
    private String errMsg;


    private RetDataEntity retData;

    /**
     * Sets err num.
     *
     * @param errNum the err num
     */
    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    /**
     * Sets err msg.
     *
     * @param errMsg the err msg
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    /**
     * Sets ret data.
     *
     * @param retData the ret data
     */
    public void setRetData(RetDataEntity retData) {
        this.retData = retData;
    }

    /**
     * Gets err num.
     *
     * @return the err num
     */
    public int getErrNum() {
        return errNum;
    }

    /**
     * Gets err msg.
     *
     * @return the err msg
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * Gets ret data.
     *
     * @return the ret data
     */
    public RetDataEntity getRetData() {
        return retData;
    }

    /**
     * The type Ret data entity.
     */
    public static class RetDataEntity {
        private String city;
        private String pinyin;
        private String citycode;
        private String date;
        private String time;
        private String postCode;
        private double longitude;
        private double latitude;
        private String altitude;
        private String weather;
        private String temp;
        private String l_tmp;
        private String h_tmp;
        private String WD;
        private String WS;
        private String sunrise;
        private String sunset;

        /**
         * Sets city.
         *
         * @param city the city
         */
        public void setCity(String city) {
            this.city = city;
        }

        /**
         * Sets pinyin.
         *
         * @param pinyin the pinyin
         */
        public void setPinyin(String pinyin) {
            this.pinyin = pinyin;
        }

        /**
         * Sets citycode.
         *
         * @param citycode the citycode
         */
        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        /**
         * Sets date.
         *
         * @param date the date
         */
        public void setDate(String date) {
            this.date = date;
        }

        /**
         * Sets time.
         *
         * @param time the time
         */
        public void setTime(String time) {
            this.time = time;
        }

        /**
         * Sets post code.
         *
         * @param postCode the post code
         */
        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        /**
         * Sets longitude.
         *
         * @param longitude the longitude
         */
        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        /**
         * Sets latitude.
         *
         * @param latitude the latitude
         */
        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        /**
         * Sets altitude.
         *
         * @param altitude the altitude
         */
        public void setAltitude(String altitude) {
            this.altitude = altitude;
        }

        /**
         * Sets weather.
         *
         * @param weather the weather
         */
        public void setWeather(String weather) {
            this.weather = weather;
        }

        /**
         * Sets temp.
         *
         * @param temp the temp
         */
        public void setTemp(String temp) {
            this.temp = temp;
        }

        /**
         * Sets l tmp.
         *
         * @param l_tmp the l tmp
         */
        public void setL_tmp(String l_tmp) {
            this.l_tmp = l_tmp;
        }

        /**
         * Sets h tmp.
         *
         * @param h_tmp the h tmp
         */
        public void setH_tmp(String h_tmp) {
            this.h_tmp = h_tmp;
        }

        /**
         * Sets wd.
         *
         * @param WD the wd
         */
        public void setWD(String WD) {
            this.WD = WD;
        }

        /**
         * Sets ws.
         *
         * @param WS the ws
         */
        public void setWS(String WS) {
            this.WS = WS;
        }

        /**
         * Sets sunrise.
         *
         * @param sunrise the sunrise
         */
        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        /**
         * Sets sunset.
         *
         * @param sunset the sunset
         */
        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        /**
         * Gets city.
         *
         * @return the city
         */
        public String getCity() {
            return city;
        }

        /**
         * Gets pinyin.
         *
         * @return the pinyin
         */
        public String getPinyin() {
            return pinyin;
        }

        /**
         * Gets citycode.
         *
         * @return the citycode
         */
        public String getCitycode() {
            return citycode;
        }

        /**
         * Gets date.
         *
         * @return the date
         */
        public String getDate() {
            return date;
        }

        /**
         * Gets time.
         *
         * @return the time
         */
        public String getTime() {
            return time;
        }

        /**
         * Gets post code.
         *
         * @return the post code
         */
        public String getPostCode() {
            return postCode;
        }

        /**
         * Gets longitude.
         *
         * @return the longitude
         */
        public double getLongitude() {
            return longitude;
        }

        /**
         * Gets latitude.
         *
         * @return the latitude
         */
        public double getLatitude() {
            return latitude;
        }

        /**
         * Gets altitude.
         *
         * @return the altitude
         */
        public String getAltitude() {
            return altitude;
        }

        /**
         * Gets weather.
         *
         * @return the weather
         */
        public String getWeather() {
            return weather;
        }

        /**
         * Gets temp.
         *
         * @return the temp
         */
        public String getTemp() {
            return temp;
        }

        /**
         * Gets l tmp.
         *
         * @return the l tmp
         */
        public String getL_tmp() {
            return l_tmp;
        }

        /**
         * Gets h tmp.
         *
         * @return the h tmp
         */
        public String getH_tmp() {
            return h_tmp;
        }

        /**
         * Gets wd.
         *
         * @return the wd
         */
        public String getWD() {
            return WD;
        }

        /**
         * Gets ws.
         *
         * @return the ws
         */
        public String getWS() {
            return WS;
        }

        /**
         * Gets sunrise.
         *
         * @return the sunrise
         */
        public String getSunrise() {
            return sunrise;
        }

        /**
         * Gets sunset.
         *
         * @return the sunset
         */
        public String getSunset() {
            return sunset;
        }
    }
}
