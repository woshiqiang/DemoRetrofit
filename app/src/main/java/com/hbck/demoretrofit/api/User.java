package com.hbck.demoretrofit.api;

/**
 * Created by Administrator on 2017-04-15.
 */

public class User {

    /**
     * EmpID : 2886
     * EmpNumber : ck1560
     * EmpName : 丁建强
     * BelongDepartmentID : 7
     * BelongServeStationSCID : 0
     * BelongServeStationZWID : 0
     * EmployeePhone :
     * EmployeePhone2 :
     * CityID : 1
     * Code : 123456789012
     * password : sjzgwbn123
     */

    public String EmpID;
    public String EmpNumber;
    public String EmpName;
    public String BelongDepartmentID;
    public String BelongServeStationSCID;
    public String BelongServeStationZWID;
    public String EmployeePhone;
    public String EmployeePhone2;
    public String CityID;
    public String Code;
    public String password;

    @Override
    public String toString() {
        return "User{" +
                "EmpID='" + EmpID + '\'' +
                ", EmpNumber='" + EmpNumber + '\'' +
                ", EmpName='" + EmpName + '\'' +
                ", BelongDepartmentID='" + BelongDepartmentID + '\'' +
                ", BelongServeStationSCID='" + BelongServeStationSCID + '\'' +
                ", BelongServeStationZWID='" + BelongServeStationZWID + '\'' +
                ", EmployeePhone='" + EmployeePhone + '\'' +
                ", EmployeePhone2='" + EmployeePhone2 + '\'' +
                ", CityID='" + CityID + '\'' +
                ", Code='" + Code + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
