package com.example.alpha.demo_opengl;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by gengchunjiang on 2018/9/28.
 */

public class GLRenderer implements GLSurfaceView.Renderer {

    private float[] mTriangleArray = {
            0f, 1f, 0f,
            -1f, -1f, 0f,
            1f, -1f, 0f
    };

    //OpenGL不对堆中数据进行操作，而是在直接内存中操作。 即操作的数据需要保存到NIO的Buffer中。
    public GLRenderer(float[] mTriangleArray) {
        this.mTriangleArray = mTriangleArray;
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

