REM OS:     Windows 10
REM IDE:    Eclipse 2019-12
REM JDK:    C:\Program Files\AdoptOpenJDK\jdk-11.0.5.10-hotspot\bin
REM JRE:    C:\Program Files (x86)\Java\jre1.8.0_231
REM JavaFX: C:\Users\Tushar\javafx-sdk-11.0.2\lib

REM My project directory structure
REM C:\Users\Tushar\Documents\eclipse-workspace-java\Milk_Weights
REM .git
REM .settings
REM application\GUI.java
REM Makefile
REM manifest.txt
REM csv
REM build.bat
REM stylesheets.css
REM screenshot1.png
REM screenshot2.png


REM My command line run command as copied from Eclipse
REM "C:\Program Files\Java\jdk-13.0.1\bin\javaw.exe" --module-path "C:\Users\Tushar\javafx-sdk-11.0.2\lib" --add-modules=javafx.controls -Dfile.encoding=Cp1252 -classpath "C:\Users\Tushar\Documents\eclipse-workspace-java\Milk_Weights\bin;C:\Users\Tushar\javafx-sdk-11.0.2\lib\javafx.base.jar;C:\Users\Tushar\javafx-sdk-11.0.2\lib\javafx.controls.jar;C:\Users\Tushar\javafx-sdk-11.0.2\lib\javafx.fxml.jar;C:\Users\Tushar\javafx-sdk-11.0.2\lib\javafx.graphics.jar;C:\Users\Tushar\javafx-sdk-11.0.2\lib\javafx.media.jar;C:\Users\Tushar\javafx-sdk-11.0.2\lib\javafx.swing.jar;C:\Users\Tushar\javafx-sdk-11.0.2\lib\javafx.web.jar;C:\Users\Tushar\javafx-sdk-11.0.2\lib\javafx-swt.jar;C:\Users\Tushar\javafx-sdk-11.0.2\lib\src.zip" application.GUI

REM build the executable jar, manifest.txt is required
jar cvmf manifest.txt executable.jar application 

REM use jar tool to create a zip file that does not include .git and project settings
REM Note 1: you will have to add other file types if they exist and are required for your project (WE WANT ALL FILES HERE)
REM Note 2: capitalization matters
REM CAUTION: all files must be explicitly included in this form (and we need all but the .git and project settings folders)
jar cvf fx.zip Makefile application *.css *.bat *.jar *.txt *.jpg *.png *.class *.java

REM include a command to run the jar file from the command line (just to see that it works!)
REM TODO: edit path so that it has the correct path for your javafx library
java --module-path "C:\Users\Tushar\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml -jar executable.jar

