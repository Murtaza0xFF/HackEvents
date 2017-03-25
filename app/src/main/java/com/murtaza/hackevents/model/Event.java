/*
 *
 *     Copyright (C)  2017  Murtaza Akbari
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.murtaza.hackevents.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("subscribe")
    @Expose
    private String subscribe;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("college")
    @Expose
    private Boolean college;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("start_timestamp")
    @Expose
    private String startTimestamp;
    @SerializedName("end_timestamp")
    @Expose
    private String endTimestamp;
    @SerializedName("start_tz")
    @Expose
    private String startTz;
    @SerializedName("end_tz")
    @Expose
    private String endTz;
    @SerializedName("start_utc_tz")
    @Expose
    private String startUtcTz;
    @SerializedName("end_utc_tz")
    @Expose
    private String endUtcTz;
    @SerializedName("cover_image")
    @Expose
    private String coverImage;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("is_hackerearth")
    @Expose
    private Boolean isHackerearth;
    @SerializedName("challenge_type")
    @Expose
    private String challengeType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getCollege() {
        return college;
    }

    public void setCollege(Boolean college) {
        this.college = college;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(String startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public String getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(String endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public String getStartTz() {
        return startTz;
    }

    public void setStartTz(String startTz) {
        this.startTz = startTz;
    }

    public String getEndTz() {
        return endTz;
    }

    public void setEndTz(String endTz) {
        this.endTz = endTz;
    }

    public String getStartUtcTz() {
        return startUtcTz;
    }

    public void setStartUtcTz(String startUtcTz) {
        this.startUtcTz = startUtcTz;
    }

    public String getEndUtcTz() {
        return endUtcTz;
    }

    public void setEndUtcTz(String endUtcTz) {
        this.endUtcTz = endUtcTz;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Boolean getIsHackerearth() {
        return isHackerearth;
    }

    public void setIsHackerearth(Boolean isHackerearth) {
        this.isHackerearth = isHackerearth;
    }

    public String getChallengeType() {
        return challengeType;
    }

    public void setChallengeType(String challengeType) {
        this.challengeType = challengeType;
    }

}
