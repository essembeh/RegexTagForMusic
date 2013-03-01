package org.essembeh.rtfm.core.properties;

public enum SpecialAttribute {
	DB_EXPORT("attribute.export"), ERROR("attribute.error"), MUSIC_TAGGED("attribute.tagged"), UI_HIDE("attribute.hide");

	private String key;

	private SpecialAttribute(String key) {
		this.key = key;
	}

	String getKey() {
		return key;
	}
}
