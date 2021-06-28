package tech.qijin.incubator.social.service;

import org.springframework.stereotype.Service;

@Service
public class StackService {

    public static String getThreadTitle(Thread currentThread) {
        StringBuilder sb = new StringBuilder("thread_name=");
        sb.append(currentThread.getName())
                .append(";id=").append(Long.toHexString(currentThread.getId()))
                .append(";is_daemon=").append(currentThread.isDaemon())
                .append(";priority=").append(currentThread.getPriority());
        return sb.toString();
    }

    public Object stack() {
        Thread currentThread = Thread.currentThread();
        StackTraceElement[] stackTraceElementArray = currentThread.getStackTrace();

        StringBuilder builder = new StringBuilder();
        builder.append(getThreadTitle(currentThread)).append("\n");

        int skip = 0;
        for (int index = skip; index < stackTraceElementArray.length; index++) {
            StackTraceElement ste = stackTraceElementArray[index];
            builder.append("        at ")
                    .append(ste.getClassName())
                    .append(".")
                    .append(ste.getMethodName())
                    .append("(")
                    .append(ste.getFileName())
                    .append(":")
                    .append(ste.getLineNumber())
                    .append(")\n");
        }

        return builder.toString();
    }
}
