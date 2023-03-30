/**
 * This class contains generated code from the Codename One Designer, DO NOT MODIFY!
 * This class is designed for subclassing that way the code generator can overwrite it
 * anytime without erasing your changes which should exist in a subclass!
 * For details about this file and how it works please read this blog post:
 * http://codenameone.blogspot.com/2010/10/ui-builder-class-how-to-actually-use.html
*/
package generated;

import com.codename1.ui.*;
import com.codename1.ui.util.*;
import com.codename1.ui.plaf.*;
import java.util.Hashtable;
import com.codename1.ui.events.*;

public abstract class StateMachineBase extends UIBuilder {
    private Container aboutToShowThisContainer;
    /**
     * this method should be used to initialize variables instead of
     * the constructor/class scope to avoid race conditions
     */
    /**
    * @deprecated use the version that accepts a resource as an argument instead
    
**/
    protected void initVars() {}

    protected void initVars(Resources res) {}

    public StateMachineBase(Resources res, String resPath, boolean loadTheme) {
        startApp(res, resPath, loadTheme);
    }

    public Container startApp(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("TextArea", com.codename1.ui.TextArea.class);
        UIBuilder.registerCustomComponent("Picker", com.codename1.ui.spinner.Picker.class);
        UIBuilder.registerCustomComponent("Label", com.codename1.ui.Label.class);
        UIBuilder.registerCustomComponent("TextField", com.codename1.ui.TextField.class);
        UIBuilder.registerCustomComponent("RadioButton", com.codename1.ui.RadioButton.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    if(resPath.endsWith(".res")) {
                        res = Resources.open(resPath);
                        System.out.println("Warning: you should construct the state machine without the .res extension to allow theme overlays");
                    } else {
                        res = Resources.openLayered(resPath);
                    }
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        if(res != null) {
            setResourceFilePath(resPath);
            setResourceFile(res);
            initVars(res);
            return showForm(getFirstFormName(), null);
        } else {
            Form f = (Form)createContainer(resPath, getFirstFormName());
            initVars(fetchResourceFile());
            beforeShow(f);
            f.show();
            postShow(f);
            return f;
        }
    }

    protected String getFirstFormName() {
        return "Main";
    }

    public Container createWidget(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("TextArea", com.codename1.ui.TextArea.class);
        UIBuilder.registerCustomComponent("Picker", com.codename1.ui.spinner.Picker.class);
        UIBuilder.registerCustomComponent("Label", com.codename1.ui.Label.class);
        UIBuilder.registerCustomComponent("TextField", com.codename1.ui.TextField.class);
        UIBuilder.registerCustomComponent("RadioButton", com.codename1.ui.RadioButton.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    res = Resources.openLayered(resPath);
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        return createContainer(resPath, "Main");
    }

    protected void initTheme(Resources res) {
            String[] themes = res.getThemeResourceNames();
            if(themes != null && themes.length > 0) {
                UIManager.getInstance().setThemeProps(res.getTheme(themes[0]));
            }
    }

    public StateMachineBase() {
    }

    public StateMachineBase(String resPath) {
        this(null, resPath, true);
    }

    public StateMachineBase(Resources res) {
        this(res, null, true);
    }

    public StateMachineBase(String resPath, boolean loadTheme) {
        this(null, resPath, loadTheme);
    }

    public StateMachineBase(Resources res, boolean loadTheme) {
        this(res, null, loadTheme);
    }

    public com.codename1.ui.TextField findSpeed2TextField(Component root) {
        return (com.codename1.ui.TextField)findByName("speed2TextField", root);
    }

    public com.codename1.ui.TextField findSpeed2TextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("speed2TextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("speed2TextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findStartButton(Component root) {
        return (com.codename1.ui.Button)findByName("startButton", root);
    }

    public com.codename1.ui.Button findStartButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("startButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("startButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLabel(Component root) {
        return (com.codename1.ui.Label)findByName("Label", root);
    }

    public com.codename1.ui.Label findLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Label", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Label", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLabel1(Component root) {
        return (com.codename1.ui.Label)findByName("Label1", root);
    }

    public com.codename1.ui.Label findLabel1() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Label1", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Label1", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer3(Component root) {
        return (com.codename1.ui.Container)findByName("Container3", root);
    }

    public com.codename1.ui.Container findContainer3() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container3", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container3", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer4(Component root) {
        return (com.codename1.ui.Container)findByName("Container4", root);
    }

    public com.codename1.ui.Container findContainer4() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container4", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container4", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer1(Component root) {
        return (com.codename1.ui.Container)findByName("Container1", root);
    }

    public com.codename1.ui.Container findContainer1() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container1", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container1", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer2(Component root) {
        return (com.codename1.ui.Container)findByName("Container2", root);
    }

    public com.codename1.ui.Container findContainer2() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container2", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container2", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findSck300PRadioButton(Component root) {
        return (com.codename1.ui.RadioButton)findByName("sck300PRadioButton", root);
    }

    public com.codename1.ui.RadioButton findSck300PRadioButton() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("sck300PRadioButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("sck300PRadioButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer5(Component root) {
        return (com.codename1.ui.Container)findByName("Container5", root);
    }

    public com.codename1.ui.Container findContainer5() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container5", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container5", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findConnectButton(Component root) {
        return (com.codename1.ui.Button)findByName("connectButton", root);
    }

    public com.codename1.ui.Button findConnectButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("connectButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("connectButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer6(Component root) {
        return (com.codename1.ui.Container)findByName("Container6", root);
    }

    public com.codename1.ui.Container findContainer6() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container6", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container6", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findTime2TextField(Component root) {
        return (com.codename1.ui.TextField)findByName("time2TextField", root);
    }

    public com.codename1.ui.TextField findTime2TextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("time2TextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("time2TextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findConsoleTextArea(Component root) {
        return (com.codename1.ui.TextArea)findByName("consoleTextArea", root);
    }

    public com.codename1.ui.TextArea findConsoleTextArea() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("consoleTextArea", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("consoleTextArea", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer(Component root) {
        return (com.codename1.ui.Container)findByName("Container", root);
    }

    public com.codename1.ui.Container findContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findSpeedLabel(Component root) {
        return (com.codename1.ui.Label)findByName("speedLabel", root);
    }

    public com.codename1.ui.Label findSpeedLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("speedLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("speedLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findSck300RadioButton(Component root) {
        return (com.codename1.ui.RadioButton)findByName("sck300RadioButton", root);
    }

    public com.codename1.ui.RadioButton findSck300RadioButton() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("sck300RadioButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("sck300RadioButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findTime1TextField(Component root) {
        return (com.codename1.ui.TextField)findByName("time1TextField", root);
    }

    public com.codename1.ui.TextField findTime1TextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("time1TextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("time1TextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findStopButton(Component root) {
        return (com.codename1.ui.Button)findByName("stopButton", root);
    }

    public com.codename1.ui.Button findStopButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("stopButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("stopButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findSpeed1TextField(Component root) {
        return (com.codename1.ui.TextField)findByName("speed1TextField", root);
    }

    public com.codename1.ui.TextField findSpeed1TextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("speed1TextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("speed1TextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findTimeLabel(Component root) {
        return (com.codename1.ui.Label)findByName("timeLabel", root);
    }

    public com.codename1.ui.Label findTimeLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("timeLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("timeLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.spinner.Picker findBtPicker(Component root) {
        return (com.codename1.ui.spinner.Picker)findByName("btPicker", root);
    }

    public com.codename1.ui.spinner.Picker findBtPicker() {
        com.codename1.ui.spinner.Picker cmp = (com.codename1.ui.spinner.Picker)findByName("btPicker", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.spinner.Picker)findByName("btPicker", aboutToShowThisContainer);
        }
        return cmp;
    }

    protected void exitForm(Form f) {
        if("Main".equals(f.getName())) {
            exitMain(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void exitMain(Form f) {
    }

    protected void beforeShow(Form f) {
    aboutToShowThisContainer = f;
        if("Main".equals(f.getName())) {
            beforeMain(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void beforeMain(Form f) {
    }

    protected void beforeShowContainer(Container c) {
        aboutToShowThisContainer = c;
        if("Main".equals(c.getName())) {
            beforeContainerMain(c);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void beforeContainerMain(Container c) {
    }

    protected void postShow(Form f) {
        if("Main".equals(f.getName())) {
            postMain(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void postMain(Form f) {
    }

    protected void postShowContainer(Container c) {
        if("Main".equals(c.getName())) {
            postContainerMain(c);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void postContainerMain(Container c) {
    }

    protected void onCreateRoot(String rootName) {
        if("Main".equals(rootName)) {
            onCreateMain();
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void onCreateMain() {
    }

    protected Hashtable getFormState(Form f) {
        Hashtable h = super.getFormState(f);
        if("Main".equals(f.getName())) {
            getStateMain(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

            return h;
    }


    protected void getStateMain(Form f, Hashtable h) {
    }

    protected void setFormState(Form f, Hashtable state) {
        super.setFormState(f, state);
        if("Main".equals(f.getName())) {
            setStateMain(f, state);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void setStateMain(Form f, Hashtable state) {
    }

    protected void handleComponentAction(Component c, ActionEvent event) {
        Container rootContainerAncestor = getRootAncestor(c);
        if(rootContainerAncestor == null) return;
        String rootContainerName = rootContainerAncestor.getName();
        Container leadParentContainer = c.getParent().getLeadParent();
        if(leadParentContainer != null && leadParentContainer.getClass() != Container.class) {
            c = c.getParent().getLeadParent();
        }
        if(rootContainerName == null) return;
        if(rootContainerName.equals("Main")) {
            if("btPicker".equals(c.getName())) {
                onMain_BtPickerAction(c, event);
                return;
            }
            if("connectButton".equals(c.getName())) {
                onMain_ConnectButtonAction(c, event);
                return;
            }
            if("sck300RadioButton".equals(c.getName())) {
                onMain_Sck300RadioButtonAction(c, event);
                return;
            }
            if("sck300PRadioButton".equals(c.getName())) {
                onMain_Sck300PRadioButtonAction(c, event);
                return;
            }
            if("consoleTextArea".equals(c.getName())) {
                onMain_ConsoleTextAreaAction(c, event);
                return;
            }
            if("speed1TextField".equals(c.getName())) {
                onMain_Speed1TextFieldAction(c, event);
                return;
            }
            if("time1TextField".equals(c.getName())) {
                onMain_Time1TextFieldAction(c, event);
                return;
            }
            if("speed2TextField".equals(c.getName())) {
                onMain_Speed2TextFieldAction(c, event);
                return;
            }
            if("time2TextField".equals(c.getName())) {
                onMain_Time2TextFieldAction(c, event);
                return;
            }
            if("startButton".equals(c.getName())) {
                onMain_StartButtonAction(c, event);
                return;
            }
            if("stopButton".equals(c.getName())) {
                onMain_StopButtonAction(c, event);
                return;
            }
        }
    }

      protected void onMain_BtPickerAction(Component c, ActionEvent event) {
      }

      protected void onMain_ConnectButtonAction(Component c, ActionEvent event) {
      }

      protected void onMain_Sck300RadioButtonAction(Component c, ActionEvent event) {
      }

      protected void onMain_Sck300PRadioButtonAction(Component c, ActionEvent event) {
      }

      protected void onMain_ConsoleTextAreaAction(Component c, ActionEvent event) {
      }

      protected void onMain_Speed1TextFieldAction(Component c, ActionEvent event) {
      }

      protected void onMain_Time1TextFieldAction(Component c, ActionEvent event) {
      }

      protected void onMain_Speed2TextFieldAction(Component c, ActionEvent event) {
      }

      protected void onMain_Time2TextFieldAction(Component c, ActionEvent event) {
      }

      protected void onMain_StartButtonAction(Component c, ActionEvent event) {
      }

      protected void onMain_StopButtonAction(Component c, ActionEvent event) {
      }

}
