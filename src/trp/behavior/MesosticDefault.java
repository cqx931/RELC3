package trp.behavior;

import rita.RiText;
import trp.layout.RiTextGrid;
import trp.reader.MachineReader;
import trp.reader.MesosticReader;

public class MesosticDefault extends DefaultVisuals {
    
    public MesosticDefault() {
       this(10f, MBLUE);
    }
    
    public MesosticDefault(float fadeTime, float[] color) {
      setFadeOutTime(fadeTime);
      setReaderColor(color);
    }
    
    public void enterWord(MachineReader mr, RiText rt) 
    {
      MesosticReader meso = (MesosticReader)mr;
      
      RiTextGrid rtg = mr.getGrid();
      
      // Reset text of direct neighbors
      RiTextGrid.resetTextFor(rtg.previousCell(rt));
      RiTextGrid.resetTextFor(rtg.nextCell(rt));
      
      if (meso.isUppercasingSelectedLetter()) 
      {
        String newText = RiTextGrid.originalTextFor(rt)
          .replaceFirst(meso.theLetter, meso.theLetter.toUpperCase());
  
        rtg.textFor(rt, newText);
      }
  
       // adjust fade time: min. of 16 + 2 sec (???)
      setFadeOutTime(16 + 4 * mr.getSpeed());

      super.enterWord(mr, rt);
      
      if (meso.isUpdatingTitleLetters())
        meso.doTitleUpdate(meso.isTitleUpperCase());
    }
  }