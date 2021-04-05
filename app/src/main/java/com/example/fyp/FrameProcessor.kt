package com.example.fyp

import com.google.mlkit.vision.common.InputImage
import com.otaliastudios.cameraview.Frame

class FrameProcessor
{
    public fun prepare_frame(frame: Frame): InputImage
    {
        //ByteArray for the captured frame
        val data = frame.data
        val image = InputImage.fromByteArray(data, frame.size.width, frame.size.height, frame.rotation, InputImage.IMAGE_FORMAT_NV21)
        return image
    }
}