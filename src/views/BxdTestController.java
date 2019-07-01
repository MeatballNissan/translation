package views;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.annotation.PostConstruct;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class BxdTestController {
    @FXML
    private TextArea putIn;

    @FXML
    private TextFlow translate;

    @FXML
    private Button transBtn;

    @FXML
    private DatePicker datePicker;

    @FXML
    private CheckBox checkBox;
    
    @FXML
    private ComboBox<Object> comboBox;
public static void main(String[] args) {
    byte[] bytes = "张".getBytes();
    for (byte b : bytes) {
        System.out.println(b);
    }
    Integer integer = Integer.parseInt("11100101", 2);
    Integer integer2 = Integer.parseInt("10111100", 2);
    Integer integer3 = Integer.parseInt("10100000", 2);
    System.out.println(integer+" " + integer2 +" " + integer3);
    System.out.println((byte)integer.intValue());
    System.out.println((byte)integer2.intValue());
    System.out.println((byte)integer3.intValue());
    byte[] byts = new byte[3];
    byts[0] = (byte)integer.intValue();
    byts[1] = (byte)integer2.intValue();
    byts[2] = (byte)integer3.intValue();
    try {
        System.out.println(new String(byts,"utf-8"));
    } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
    @PostConstruct
    public void init() {

        transBtn.setOnMouseClicked(event -> {
            translate.getChildren().clear();
            if ("".equals(putIn.getText())) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("内容为空");
                alert.showAndWait();
                return;
            }
            try {

                String ss = putIn.getText();

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ByteArrayInputStream inputStream = new ByteArrayInputStream(ss.getBytes());
                int read = -1;
                byte[] byte3 = new byte[3];
                while ((read = inputStream.read()) > -1) {
                    if (read == '\\') {
                        try {
                            inputStream.read(byte3);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String string = new String(byte3);
                        int parseInt = Integer.parseInt(new String(byte3), 8);
                        outputStream.write(Integer.parseInt(new String(byte3), 8));
                    } else {
                        outputStream.write(read);
                    }
                }
                for (byte b : byte3) {
                    System.out.println(b);
                }
                String decodeMessage = null;
                try {
                    decodeMessage = new String(outputStream.toByteArray(), "utf-8");
                    byte[] bytes = decodeMessage.getBytes("utf-8");
                    System.out.println(bytes);
                } catch (UnsupportedEncodingException e) {

                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                }

                Text text = new Text(decodeMessage);
                text.setFill(Color.GREEN);
                text.setFont(Font.font("Helvetica", FontPosture.ITALIC, 20));
                translate.getChildren().addAll(text);
            } catch (Exception e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("输入不正规：" + e.getMessage());
                alert.showAndWait();
            }
        });
    }
}
