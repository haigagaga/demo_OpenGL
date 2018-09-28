package com.example.alpha.demo_opengl;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by gengchunjiang on 2018/9/28.
 *
 *
 *
 *
 *      OpenGL的变换其实都是通过矩阵相乘来实现的。
 *
 *
 *
 *
 */

public class GLRenderer implements GLSurfaceView.Renderer {

    private FloatBuffer mTriangleBuffer;
    private float[] mTriangleArray = {
            0f, 1f, 0f,
            -1f, -1f, 0f,
            1f, -1f, 0f
    };

    //OpenGL不对堆中数据进行操作，而是在直接内存中操作。 即操作的数据需要保存到NIO的Buffer中。
    public GLRenderer(float[] mTriangleArray) {
        //先初始化buffer，数组的长度*4，因为一个float占4个字节
        ByteBuffer bb = ByteBuffer.allocateDirect(mTriangleArray.length * 4);
        //以本机字节顺序来修改此缓冲区的字节顺序
        bb.order(ByteOrder.nativeOrder());
        mTriangleBuffer = bb.asFloatBuffer();
        //将给定float[]数据从当前位置开始，依次写入此缓冲区
        mTriangleBuffer.put(mTriangleArray);
        //设置此缓冲区的位置。如果标记已定义并且大于新的位置，则要丢弃该标记。
        mTriangleBuffer.position(0);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //设置清屏颜色   rgba取值范围都是0-1
        gl.glClearColor(1f, 0f, 0f, 0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //设置视角窗口大小
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //使用glClearColor函数所设置的颜色进行清屏
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    }
}

