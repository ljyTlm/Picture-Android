package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImgUtil {

    /**
     * 获取图片每个像素点的rgb值
     * @param imgPath
     * @return
     */
    static int[][][] getRgb(String imgPath) {
        BufferedImage img = getImgBufferByPath(imgPath);
        int width = img.getWidth();
        int height = img.getHeight();
        int[][][] rgb = new int[height][width][3];
        for(int i = 0; i < height; i ++) {
            for(int j = 0; j < width; j ++) {
                int val = img.getRGB(i, j);
                rgb[i][j][0] = (val & 0xff0000) >> 16;
                rgb[i][j][1] = (val & 0xff00) >> 8;
                rgb[i][j][2] = (val & 0xff);
            }
        }
        return rgb;
    }

    /**
     * 将像素点灰度化、使用的是加权算法 rgb比例分别为0.3、0.59、011
     * @param r
     * @param g
     * @param b
     * @return
     */
    static int getPointRgbGreyVal(int r, int g, int b) {
        return (int)(0.30*r+0.59*g+0.11*b);
    }

    /**
     * 将整张图片灰度化
     * @param img
     * @return
     */
    static int[][] getImgRgbGreyVal(int[][][] img) {
        int[][] imgGrey = new int[img.length][img[0].length];
        for(int i = 0 ; i < img.length; i ++) {
            for(int j = 0; j < img[i].length; j ++) {
                imgGrey[i][j] = getPointRgbGreyVal(img[i][j][0],img[i][j][1],img[i][j][2]);
            }
        }
        return imgGrey;
    }

    /**
     * 获取图片的io流
     * @param imgPath
     * @return
     */
    static private BufferedImage getImgBufferByPath(String imgPath) {
        try {
            return ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
