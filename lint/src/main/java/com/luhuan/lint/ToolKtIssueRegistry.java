package com.luhuan.lint;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.ApiKt;
import com.android.tools.lint.detector.api.Issue;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class ToolKtIssueRegistry extends IssueRegistry {
    @NotNull
    @Override
    public List<Issue> getIssues() {
        return Arrays.asList(LoggerDetector.getIssue(),ToastDetector.getIssue());
    }

    @Override
    public int getApi() {
        return ApiKt.CURRENT_API;
    }
}
