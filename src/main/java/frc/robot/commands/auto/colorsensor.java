package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.I2C;

public class colorsensor{
    I2C sport;
    byte[] retval = new byte[2];
    colorsensor(int addr){
      sport = new I2C(Port.kOnboard, addr);
      sport.write(0x00, 0b00000110);
      sport.write(0x04, 0b01000000);
    }

    //converts the two bytes returned(lsb) by the i2c read into an int(msb)
    public int parseBytes(byte[] b){
      int raw = (int)makeShort(b[1], b[0]);
      if(raw > 0)
        return raw;
      else
        return 32768 + (32768 + raw);
    }

    //yoink't from decompiled java.nio.bits
    private static short makeShort(byte b1, byte b2) {
      return (short)(b1 << 8 | b2 & 0xFF);
  }

    public void configLS(byte params){
      sport.write(0x04, params);
    }

    public int getR(){
      sport.read(0x13, 2, retval);
      return parseBytes(retval);
    }

    public int getG(){
      sport.read(0x0D, 2, retval);
      return parseBytes(retval);
    }

    public int getB(){
      sport.read(0x10, 2, retval);
      return parseBytes(retval);
    }

    public int[] getRGB(){
      int[] vals = new int[3];
      //R
      sport.read(0x15, 2, retval);
      vals[0] = parseBytes(retval);
      //G
      sport.read(0x0F, 2, retval);
      vals[1] = parseBytes(retval);
      //B
      sport.read(0x12, 2, retval);
      vals[2] = parseBytes(retval);

      return vals;
    }

    public float[] getHSV(){
      float[] hsv = new float[3];
      RGBtoHSB(this.getR(), this.getG(), this.getB(), hsv);
      return hsv;
    }

    //whole method yoinked from java source (java.awt.color)
    public static float[] RGBtoHSB(int red, int green, int blue, float array[]){
      if (array == null)
        array = new float[3];
      // Calculate brightness.
      int min;
      int max;
      if (red < green){
        min = red;
        max = green;
      }
      else{
        min = green;
        max = red;
      }
      if (blue > max)
        max = blue;
      else if (blue < min)
        min = blue;
      array[2] = max / 255f;
      // Calculate saturation.
      if (max == 0)
        array[1] = 0;
      else
        array[1] = ((float) (max - min)) / ((float) max);
      // Calculate hue.
      if (array[1] == 0)
        array[0] = 0;
      else{
        float delta = (max - min) * 6;
        if (red == max)
          array[0] = (green - blue) / delta;
        else if (green == max)
          array[0] = 1f / 3 + (blue - red) / delta;
        else
          array[0] = 2f / 3 + (red - green) / delta;
        if (array[0] < 0)
          array[0]++;
        }
      return array;
    }
  }