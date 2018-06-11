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

public class ToastDetector extends Detector implements Detector.UastScanner {

    private static final Issue ISSUE_TOAST =
            Issue.create("ToastNotToastKt",
                    "toast时,应该使用ToastKt",
                    "已经对Toast进行了统一管理",
                    Category.MESSAGES, 9, Severity.WARNING,
                    new Implementation(ToastDetector.class, Scope.JAVA_FILE_SCOPE));

    static Issue getIssue() {
        return ISSUE_TOAST;
    }

    @Nullable
    @Override
    public List<String> getApplicableMethodNames() {
        return Arrays.asList("makeText");
    }

    @Override
    public void visitMethod(JavaContext context, UCallExpression call, PsiMethod method) {
        JavaEvaluator evaluator = context.getEvaluator();
        if (evaluator.isMemberInClass(method, "android.widget.Toast")) {
            LintFix fix = quickFixIssueLog(call);
            context.report(ISSUE_TOAST, call, context.getLocation(call), "Using 'Toast' instead " +
                    "of 'ToastKt'", fix);
        }
    }

    private LintFix quickFixIssueLog(UCallExpression logCall) {
        List<UExpression> arguments = logCall.getValueArguments();
        UExpression content = arguments.get(0);
        UExpression msg = arguments.get(1);
        String fixSource = "com.luhuan.tool.ToastKt.toast("
                + content.asSourceString() + ","
                + msg.asSourceString() + ");//";

        String logCallSource = logCall.asSourceString();
        LintFix.GroupBuilder fixGrouper = fix().group();
        fixGrouper.add(
                fix().replace().text(logCallSource).shortenNames().reformat(true).with(fixSource).build());
        return fixGrouper.build();
    }

}
