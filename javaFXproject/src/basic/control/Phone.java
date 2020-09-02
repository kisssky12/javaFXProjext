package basic.control;

import javafx.beans.property.SimpleStringProperty;

public class Phone {
	SimpleStringProperty smartPhone;
	SimpleStringProperty image;

	public String getSmartPhone() {
		return smartPhone.get();
	}

	public void setSmartPhone(SimpleStringProperty smartPhone) {
		this.smartPhone = smartPhone;
	}

	public SimpleStringProperty getImage() {
		return image;
	}

	public void setImage(SimpleStringProperty image) {
		this.image = image;
	}

	public Phone(String smartPhone, String image) {
		super();
		this.smartPhone = new SimpleStringProperty(smartPhone);
		this.image = new SimpleStringProperty(image);
	}

}
