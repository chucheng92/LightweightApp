package com.tinymood.wechat.message.req;

/**
 * @author nothankyou
 * @date 2017-02-03 14:02:39
 *
 */
public class LocationMessage extends BaseReqMessage {
	// 纬度
	private String location_x;

	// 经度
	private String location_y;

	// 缩放大小
	private String scale;

	// 地理位置信息
	private String label;

    public String getLocation_x() {
        return location_x;
    }

    public void setLocation_x(String location_x) {
        this.location_x = location_x;
    }

    public String getLocation_y() {
        return location_y;
    }

    public void setLocation_y(String location_y) {
        this.location_y = location_y;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
