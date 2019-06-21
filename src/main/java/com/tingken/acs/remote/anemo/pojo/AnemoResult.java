/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.remote.anemo.pojo;

/**
 * The purpose of this class is to encapsulate the result in getting
 * anemometer data.
 */
public class AnemoResult {
    public static class AnemoDataItem {
        private String factory;
        private String code2 = null;
        private String code1;
        private String maxWindTime = null;
        private String sensorUId;
        private String alarmTime;
        private String windSpeedTime;
        private float alarmThreshold;
        private String label;
        private float type;
        private String equipmentLabel = null;
        private String todayMaxTime;
        private float todayMaxSpeed;
        private float avgSpeed;
        private float valid;
        private String equipmentUId;
        private float alarmState;
        private String des = null;
        private String maxWindSpeed = null;
        private String name;
        private float alarmThreshold2;
        private String modelNumber = null;
        private float windSpeed;
        private float alarmWindSpeed;


       // Getter Methods 

        public String getFactory() {
          return factory;
        }

        public String getCode2() {
          return code2;
        }

        public String getCode1() {
          return code1;
        }

        public String getMaxWindTime() {
          return maxWindTime;
        }

        public String getSensorUId() {
          return sensorUId;
        }

        public String getAlarmTime() {
          return alarmTime;
        }

        public String getWindSpeedTime() {
          return windSpeedTime;
        }

        public float getAlarmThreshold() {
          return alarmThreshold;
        }

        public String getLabel() {
          return label;
        }

        public float getType() {
          return type;
        }

        public String getEquipmentLabel() {
          return equipmentLabel;
        }

        public String getTodayMaxTime() {
          return todayMaxTime;
        }

        public float getTodayMaxSpeed() {
          return todayMaxSpeed;
        }

        public float getAvgSpeed() {
          return avgSpeed;
        }

        public float getValid() {
          return valid;
        }

        public String getEquipmentUId() {
          return equipmentUId;
        }

        public float getAlarmState() {
          return alarmState;
        }

        public String getDes() {
          return des;
        }

        public String getMaxWindSpeed() {
          return maxWindSpeed;
        }

        public String getName() {
          return name;
        }

        public float getAlarmThreshold2() {
          return alarmThreshold2;
        }

        public String getModelNumber() {
          return modelNumber;
        }

        public float getWindSpeed() {
          return windSpeed;
        }

        public float getAlarmWindSpeed() {
          return alarmWindSpeed;
        }

       // Setter Methods 

        public void setFactory( String factory ) {
          this.factory = factory;
        }

        public void setCode2( String code2 ) {
          this.code2 = code2;
        }

        public void setCode1( String code1 ) {
          this.code1 = code1;
        }

        public void setMaxWindTime( String maxWindTime ) {
          this.maxWindTime = maxWindTime;
        }

        public void setSensorUId( String sensorUId ) {
          this.sensorUId = sensorUId;
        }

        public void setAlarmTime( String alarmTime ) {
          this.alarmTime = alarmTime;
        }

        public void setWindSpeedTime( String windSpeedTime ) {
          this.windSpeedTime = windSpeedTime;
        }

        public void setAlarmThreshold( float alarmThreshold ) {
          this.alarmThreshold = alarmThreshold;
        }

        public void setLabel( String label ) {
          this.label = label;
        }

        public void setType( float type ) {
          this.type = type;
        }

        public void setEquipmentLabel( String equipmentLabel ) {
          this.equipmentLabel = equipmentLabel;
        }

        public void setTodayMaxTime( String todayMaxTime ) {
          this.todayMaxTime = todayMaxTime;
        }

        public void setTodayMaxSpeed( float todayMaxSpeed ) {
          this.todayMaxSpeed = todayMaxSpeed;
        }

        public void setAvgSpeed( float avgSpeed ) {
          this.avgSpeed = avgSpeed;
        }

        public void setValid( float valid ) {
          this.valid = valid;
        }

        public void setEquipmentUId( String equipmentUId ) {
          this.equipmentUId = equipmentUId;
        }

        public void setAlarmState( float alarmState ) {
          this.alarmState = alarmState;
        }

        public void setDes( String des ) {
          this.des = des;
        }

        public void setMaxWindSpeed( String maxWindSpeed ) {
          this.maxWindSpeed = maxWindSpeed;
        }

        public void setName( String name ) {
          this.name = name;
        }

        public void setAlarmThreshold2( float alarmThreshold2 ) {
          this.alarmThreshold2 = alarmThreshold2;
        }

        public void setModelNumber( String modelNumber ) {
          this.modelNumber = modelNumber;
        }

        public void setWindSpeed( float windSpeed ) {
          this.windSpeed = windSpeed;
        }

        public void setAlarmWindSpeed( float alarmWindSpeed ) {
          this.alarmWindSpeed = alarmWindSpeed;
        }
    }

    public static class Data {
        private AnemoDataItem[] array = new AnemoDataItem[0];

        public AnemoDataItem[] getArray() {
            return array;
        }

        public void setArray(AnemoDataItem[] array) {
            this.array = array;
        }
    }

    private int errcode;
    private String errmsg;
    Data DataObject = new Data();

    // Getter Methods 

    public int getErrcode() {
        return errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public Data getData() {
        return DataObject;
    }

    // Setter Methods 

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public void setData(Data dataObject) {
        this.DataObject = dataObject;
    }
}
