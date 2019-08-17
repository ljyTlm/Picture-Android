package application;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class GameScript {


    public static void main(String[] args) throws Exception{
        int size = 50;
//        createImg(size);
        long[] hashs = getHashArray(size);
        Arrays.sort(hashs);
        System.out.println(Arrays.toString(hashs));
        System.out.println(hashs[size-1]-hashs[0]);
        //14870803-14889240
    }

    private static long[] getHashArray(int size) throws Exception{
        long[] hashs = new long[size];
        int[] rgb = new int[3];
        int index = 0;
        for(int i = 0; i < size; i ++) {
            String imgDir = "C:/Users/lenovo/Desktop/game/"+i+".png";
            BufferedImage reader = ImageIO.read(new File(imgDir));
            int width = reader.getWidth();
            int height = reader.getHeight();
            int minx = reader.getMinX();
            int miny = reader.getMinY();
            System.out.println("width=" + width + ",height=" + height + ".");
            System.out.println("minx=" + minx + ",miniy=" + miny + ".");
            long hash = 0;
            for (int j = minx; j < width-2; j +=2) {
                for (int z = miny; z < height-2; z +=2) {
                    int val = rgb[0] + rgb[1] + rgb[2];
                    int vague = getRGB(reader.getRGB(j, z))+
                            getRGB(reader.getRGB(j+1, z))+
                            getRGB(reader.getRGB(j, z+1))+
                            getRGB(reader.getRGB(j+1, z+1));
                    hash = hash+vague/4;

                }
            }
            hashs[index++] = hash;
        }
        return hashs;
    }

    private static int getRGB(int rgb) {
        int r = (rgb & 0xff0000) >> 16;
        int g = (rgb & 0xff00) >> 8;
        int b = (rgb & 0xff);
//        return (r+g+b)/3;
//        return r;
        return (int)(0.30*r+0.59*g+0.11*b);
    }

    private static void createImg(int size) throws Exception {
        String dir = "/storage/emulated/0/";
        for(int i = 0; i < size; i ++) {
            String command =
                    "cmd /c adb shell screencap -p /storage/emulated/0/"+i+".png & " +
                            "adb pull /storage/emulated/0/"+i+".png C:/Users/lenovo/Desktop/game/"+i+".png & "+
                            "adb shell rm /storage/emulated/0/"+i+".png";
            Runtime.getRuntime().exec(command);
            System.out.println("我截到了第"+i+"张图");
            Thread.sleep(2000);
        }
    }
}

