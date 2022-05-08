package com.secman.application.dto;

public class DemographyDto {

    private int to25;
    private int from25to29;
    private int from30to39;
    private int from40to59;
    private int from60;

    public DemographyDto(int to25, int from25to29, int from30to39, int from40to59, int from60) {
        this.to25 = to25;
        this.from25to29 = from25to29;
        this.from30to39 = from30to39;
        this.from40to59 = from40to59;
        this.from60 = from60;
    }

    public int getTo25() {
        return to25;
    }

    public void setTo25(int to25) {
        this.to25 = to25;
    }

    public int getFrom25to29() {
        return from25to29;
    }

    public void setFrom25to29(int from25to29) {
        this.from25to29 = from25to29;
    }

    public int getFrom30to39() {
        return from30to39;
    }

    public void setFrom30to39(int from30to39) {
        this.from30to39 = from30to39;
    }

    public int getFrom40to59() {
        return from40to59;
    }

    public void setFrom40to59(int from40to59) {
        this.from40to59 = from40to59;
    }

    public int getFrom60() {
        return from60;
    }

    public void setFrom60(int from60) {
        this.from60 = from60;
    }
}
