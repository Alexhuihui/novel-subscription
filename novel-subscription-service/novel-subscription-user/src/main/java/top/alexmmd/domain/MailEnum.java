package top.alexmmd.domain;

/**
 * @author 汪永晖
 */
public enum MailEnum {

    MAGAZINE("<div style=\"background:#17212e;padding:12px;border-radius:8px;max-width:800px;margin:0 auto;color:#c6d4df;\">\n" +
            "\t\t<div style=\"font-size:20px;font-weight:bold;\">Hi, %s:</div>\n" +
            "\t\t<div style=\"font-size:15px;font-weight:bold;text-align:center;margin:20px auto;color:#66c0f4;\">\n" +
            "\t\t\t%s\n" +
            "\t\t</div>\n" +
            "\t\t<div style=\"text-align:right;\">\n" +
            "\t\t\ttip:此链接7天内有效，请尽快下载\n" +
            "\t\t</div>\n" +
            "\t</div>"),
    NOTIFY_ALL("<div style=\"background:#17212e;padding:12px;border-radius:8px;max-width:800px;margin:0 auto;color:#c6d4df;\">\n" +
            "\t\t<div style=\"font-size:20px;font-weight:bold;\">Hi, %s:</div>\n" +
            "\t\t<div style=\"font-size:15px;font-weight:bold;text-align:center;margin:20px auto;color:#66c0f4;\">\n" +
            "\t\t\t%s\n" +
            "\t\t</div>\n" +
            "\t</div>");

    private String content;

    MailEnum(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
