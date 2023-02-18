//package com.sahilssoft.s_apps;
//
//import android.Manifest;
//import android.content.pm.PackageManager;
//import android.media.AudioFormat;
//import android.media.AudioRecord;
//import android.media.MediaRecorder;
//import android.os.AsyncTask;
//import android.util.Log;
//
//import androidx.core.app.ActivityCompat;
//
//public class MyRecorder extends AsyncTask<Void, short[], Void> {
//
//    int blockSize = 2048;// = 256;
//    private static final int RECORDER_SAMPLERATE = 8000;
//    private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
//    private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
//    int BufferElements2Rec = 1024; // want to play 2048 (2K) since 2 bytes we use only 1024
//    int BytesPerElement = 2;
//
//
//    @Override
//    protected Void doInBackground(Void... params) {
//
//        try {
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return TODO;
//            }
//            final AudioRecord audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
//                    RECORDER_SAMPLERATE, RECORDER_CHANNELS,
//                    RECORDER_AUDIO_ENCODING, BufferElements2Rec * BytesPerElement);
//            if (audioRecord == null) {
//                return null;
//            }
//
//            final short[] buffer = new short[blockSize];
//            final double[] toTransform = new double[blockSize];
//            audioRecord.startRecording();
//            while (started) {
//                Thread.sleep(100);
//                final int bufferReadResult = audioRecord.read(buffer, 0, blockSize);
//                publishProgress(buffer);
//            }
//            audioRecord.stop();
//            audioRecord.release();
//        } catch (Throwable t) {
//            Log.e("AudioRecord", "Recording Failed");
//        }
//        return null;
//    }
//
//
//    @Override
//    protected void onProgressUpdate(short[]... buffer) {
//        super.onProgressUpdate(buffer);
//        float freq = calculate(RECORDER_SAMPLERATE, buffer[0]);
//    }
//
//
//    public static float calculate(int sampleRate, short[] audioData) {
//        int numSamples = audioData.length;
//        int numCrossing = 0;
//        for (int p = 0; p < numSamples - 1; p++) {
//            if ((audioData[p] > 0 && audioData[p + 1] <= 0) ||
//                    (audioData[p] < 0 && audioData[p + 1] >= 0)) {
//                numCrossing++;
//            }
//        }
//
//
//        float numSecondsRecorded = (float) numSamples / (float) sampleRate;
//        float numCycles = numCrossing / 2;
//        float frequency = numCycles / numSecondsRecorded;
//
//
//        return frequency;
//    }
//
//}