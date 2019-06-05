package themes.medieval

import com.jme3.math.Vector3f
import com.simsilica.lemur.*
import com.simsilica.lemur.Button.ButtonAction
import com.simsilica.lemur.component.IconComponent
import com.simsilica.lemur.component.TbtQuadBackgroundComponent
import com.simsilica.lemur.effect.Effect

//Globals
def ltBrownBorderedBg = TbtQuadBackgroundComponent.create(
        texture(name: "themes/medieval/img/panel_beige.png", generateMips: false),
        1, 20, 32, 48, 75, 1f, false)
def darkBrownBorderedBg = TbtQuadBackgroundComponent.create(
        texture(name: "themes/medieval/img/panel_brown.png", generateMips: false),
        1, 20, 32, 48, 75, 1f, false)
def grayBorderedBg = TbtQuadBackgroundComponent.create(
        texture(name: "themes/medieval/img/panel_blue.png", generateMips: false),
        1, 20, 32, 48, 75, 1f, false)

def ltBrownInsetBg = TbtQuadBackgroundComponent.create(
        texture(name: "themes/medieval/img/panelInset_beige.png", generateMips: false),
        1, 17, 28, 44, 71, 1f, false)

def grayButtonBg = TbtQuadBackgroundComponent.create(
        texture(name: "themes/medieval/img/buttonSquare_blue.png", generateMips: false),
        1, 7, 8, 38, 45, 1f, false)
def grayPressedButtonBg = TbtQuadBackgroundComponent.create(
        texture(name: "themes/medieval/img/buttonSquare_blue_pressed.png", generateMips: false),
        1, 7, 4, 38, 41, 1f, false)

def barBg = TbtQuadBackgroundComponent.create(
        texture(name: "themes/medieval/img/barBack.png", generateMips: false),
        1, 9, 0, 27, 18, 1f, false)
def orangeBarBg = TbtQuadBackgroundComponent.create(
        texture(name: "themes/medieval/img/barRed.png", generateMips: false),
        1, 9, 0, 27, 18, 1f, false)
def blueBarBg = TbtQuadBackgroundComponent.create(
        texture(name: "themes/medieval/img/barBlue.png", generateMips: false),
        1, 9, 0, 27, 18, 1f, false)
def greenBarBg = TbtQuadBackgroundComponent.create(
        texture(name: "themes/medieval/img/barGreen.png", generateMips: false),
        1, 9, 0, 27, 18, 1f, false)

def selectedBg = TbtQuadBackgroundComponent.create(
        texture(name: "themes/medieval/img/selected.png", generateMips: false),
        1, 9, 0, 27, 18, 1f, false)

def checkOnIcon = new IconComponent("themes/medieval/img/checkbox_on.png", 0.3, 2, 2, 1, false)
def checkOffIcon = new IconComponent("themes/medieval/img/checkbox_off.png", 0.3, 2, 2, 1, false)

def darkTextColor = color(0.341, 0.322, 0.275, 1.0)
def lightTextColor = color(0.831, 0.847, 0.914, 1.0)

def pressedCommand = new Command<Button>() {
    void execute(Button source) {
        if (source.isPressed()) {
            source.setBackground(grayPressedButtonBg.clone())
            source.getPreferredSize().y -= 4
        } else {
            source.setBackground(grayButtonBg.clone())
            source.getPreferredSize().y += 4
        }
    }
}

def repeatCommand = new Command<Button>() {
    private long startTime
    private long lastClick

    void execute(Button source) {
        // Only do the repeating click while the mouse is
        // over the button (and pressed of course)
        if (source.isPressed() && source.isHighlightOn()) {
            long elapsedTime = System.currentTimeMillis() - startTime
            // After half a second pause, click 8 times a second
            if (elapsedTime > 500) {
                if (elapsedTime - lastClick > 125) {
                    source.click();

                    // Try to quantize the last click time to prevent drift
                    lastClick = ((elapsedTime - 500) / 125) * 125 + 500
                }
            }
        } else {
            startTime = System.currentTimeMillis()
            lastClick = 0
        }
    }
};

def stdButtonCommands = [
        (ButtonAction.Down): [pressedCommand],
        (ButtonAction.Up)  : [pressedCommand]
]

def sliderButtonCommands = [
        (ButtonAction.Hover): [repeatCommand]
]

//Basics
selector("medieval") {
    fontSize = 16
}

selector("container", "medieval") {
    background = darkBrownBorderedBg.clone()
    background.setMargin(10, 10)
}

selector("darkContainer", "medieval") {
    background = grayBorderedBg.clone()
    background.setMargin(10, 10)
}

selector("insetContainer", "medieval") {
    background = ltBrownInsetBg.clone()
    background.setMargin(10, 10)
}

selector("label", "medieval") {
    insets = new Insets3f(2, 2, 2, 2)
    color = darkTextColor.clone()
    textVAlignment = VAlignment.Center
}

selector("lightLabel", "medieval") {
    insets = new Insets3f(2, 2, 2, 2)
    color = lightTextColor.clone()
    textVAlignment = VAlignment.Center
}

//More complex elements
selector("button", "medieval") {
    background = grayButtonBg.clone()
    insets = new Insets3f(2, 2, 2, 2)
    color = lightTextColor.clone()
    focusColor = color
    highlightColor = color(0.82, 0.749, 0.561, 1.0)

    textVAlignment = VAlignment.Center
    textHAlignment = HAlignment.Center

    buttonCommands = stdButtonCommands
}

selector("slider.thumb.button", "medieval") {
    text = ""
    buttonCommands = null
}

selector("slider.up.button", "medieval") {
    buttonCommands = sliderButtonCommands
}

selector("slider.down.button", "medieval") {
    buttonCommands = sliderButtonCommands
}

selector("checkbox", "medieval"){
    onView = checkOnIcon.clone()
    offView = checkOffIcon.clone()
    insets = new Insets3f(2,2,0,2)
    color = lightTextColor.clone()
    preferredSize = new Vector3f(50, 50, 0)
}

//Progress bars start here
selector("orangeBar.value", "medieval") {
    background = orangeBarBg.clone()
    background.setMargin(0, 0)
}
selector("orangeBar.container", "medieval") {
    background = barBg.clone()
    background.setMargin(0, 0)
}
selector("orangeBar.label", "medieval"){
    textHAlignment = HAlignment.Center
    textVAlignment = VAlignment.Center
    insets = new Insets3f(1, 0, 1, 0)
}

selector("blueBar.value", "medieval") {
    background = blueBarBg.clone()
    background.setMargin(0, 0)
}
selector("blueBar.container", "medieval") {
    background = barBg.clone()
    background.setMargin(0, 0)
}
selector("blueBar.label", "medieval"){
    textHAlignment = HAlignment.Center
    textVAlignment = VAlignment.Center
    insets = new Insets3f(1, 0, 1, 0)
}

selector("greenBar.value", "medieval") {
    background = greenBarBg.clone()
    background.setMargin(0, 0)
}
selector("greenBar.container", "medieval") {
    background = barBg.clone()
    background.setMargin(0, 0)
}
selector("greenBar.label", "medieval"){
    textHAlignment = HAlignment.Center
    textVAlignment = VAlignment.Center
    insets = new Insets3f(1, 0, 1, 0)
}

//Listbox starts here
selector("list.container", "medieval") {
    background = ltBrownInsetBg.clone()
    background.setMargin(10, 10)
}
selector("list.items", "medieval") {
    insets = new Insets3f(0, 0, 0, 10)
}
selector("list.item", "medieval") {
    insets = new Insets3f(2, 2, 2, 2)
    color = darkTextColor.clone()
    textVAlignment = VAlignment.Center
}
selector("list.selector", "medieval") {
    background = selectedBg.clone()
}