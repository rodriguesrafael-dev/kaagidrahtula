module kaagidrahtula {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires de.jensd.fx.glyphs.commons;
    requires de.jensd.fx.glyphs.materialdesignicons;
    requires de.jensd.fx.glyphs.fontawesome;
    requires lombok;
    requires org.postgresql.jdbc;
    requires java.sql;
    
    opens application to javafx.graphics, javafx.fxml;
    opens backend.model.entity to javafx.base;
    opens frontend.view to javafx.graphics, javafx.fxml;
}
