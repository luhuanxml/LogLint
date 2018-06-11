package com.luhuan.lint;

import com.android.tools.lint.client.api.JavaEvaluator;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.LintFix;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.psi.PsiMethod;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UCallExpression;
import org.jetbrains.uast.UExpression;

import java.util.Arrays;
import java.util.List;

public class LoggerDetector extends Detector implements Detector.UastScanner {

    private static final Issue ISSUE_LOG =
            Issue.create("LogNotLogKt",
                    "打印log时,应该使用LogKt进行打印",
                    "已经对log进行了统一管理",
                    Category.MESSAGES, 9, Severity.WARNING,
                    new Implementation(LoggerDetector.class, Scope.JAVA_FILE_SCOPE));

    static Issue getIssue() {
        return ISSUE_LOG;
    }

    @Nullable
    @Override
    public List<String> getApplicableMethodNames() {
        return Arrays.asList("d", "e");
    }

    @Override
    public void visitMethod(JavaContext context, UCallExpression call, PsiMethod method) {
        JavaEvaluator evaluator = context.getEvaluator();
        if (evaluator.isMemberInClass(method, "android.util.Log")) {
            LintFix fix = quickFixIssueToast(call);
            context.report(ISSUE_LOG, call, context.getLocation(call), "Using 'Log' instead of " +
                    "'LogKt'", fix);
        }
    }

    private LintFix quickFixIssueToast(UCallExpression logCall) {
        List<UExpression> arguments = logCall.getValueArguments();
        UExpression tag = arguments.get(0);
        UExpression msg = arguments.get(1);
        String fixSource;
        if ("e".equals(logCall.getMethodName())) { //打印异常日志
            fixSource = "com.luhuan.tool.LogKt.loge(" + tag.asSourceString() + "," + msg
                    .asSourceString() + ")";
        }else { //打印debug一般日志
            fixSource= "com.luhuan.tool.LogKt.logd(" + tag.asSourceString() + "," + msg
                    .asSourceString() + ")";
        }

        String logCallSource = logCall.asSourceString();
        LintFix.GroupBuilder fixGrouper = fix().group();
        fixGrouper.add(
                fix().replace().text(logCallSource).shortenNames().reformat(true).with(fixSource).build());
        return fixGrouper.build();
    }

}
