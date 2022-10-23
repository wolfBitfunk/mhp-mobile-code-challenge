/*
* Copyright (c) 2022. Wolf-Martell Montwé
*/

@file:Repository("https://repo.maven.apache.org")

import systems.danger.kotlin.danger
import systems.danger.kotlin.fail
import systems.danger.kotlin.message
import systems.danger.kotlin.onGitHub
import systems.danger.kotlin.warn

danger(args) {
    val regexFeatureBranch =
        "(?:feature\\/(?:[A-Z]{2,8}-\\d{1,6}\\/)?(?:add|change|remove|fix|bump|security)-[a-z0-9-.]*)"
            .toRegex()
    val regexReleaseBranch =
        "(?:release\\/(?:\\d{1,3}\\.\\d{1,3}(?:\\.\\d{1,3})?)(?:\\/prepare-\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})?)"
            .toRegex()
    val regexDependabotBranch = "dependabot/(.*)".toRegex()
    val regexRenovateBranch = "renovate/(.*)".toRegex()

    val regexFeatureTitle =
        "(?:(?:\\[[A-Z]{2,8}-\\d{1,6}\\]\\s)?(?:Add|Change|Remove|Fix|Bump|Security)\\s.*)"
            .toRegex()
    val regexReleaseTitle =
        "(?:(?:Prepare )?Release \\d{1,3}\\.\\d{1,3}\\.\\d{1,3})"
            .toRegex()

    val lineCountInform = 300
    val lineCountWarn = 600
    val lineCountFail = 1200

    val pullRequestBodyMinLines = 20

    val allSourceFiles = git.modifiedFiles + git.createdFiles
    val isChangelogUpdated = allSourceFiles.contains("CHANGELOG.md")

    onGitHub {
        val branchName = pullRequest.head.label.substringAfter(":")
        val isFeatureBranch = regexFeatureBranch.matches(branchName)
        val isReleaseBranch = regexReleaseBranch.matches(branchName)
        val isDependabotBranch = regexDependabotBranch.matches(branchName)
        val isRenovateBranch = regexRenovateBranch.matches(branchName)

        val isFeatureTitle = regexFeatureTitle.matches(pullRequest.title)
        val isReleaseTitle = regexReleaseTitle.matches(pullRequest.title)

        if (!isFeatureBranch && !isReleaseBranch && !isDependabotBranch && !isRenovateBranch) {
            fail(
                "Branch name is not following our pattern:\n" +
                    "\nrelease/1.2(.3)(/prepare-1.2.3)\n" +
                    "\nfeature/(ISSUE-123)/add|change|remove|fix|bump|security-feature-title\n" +
                    "\n\n" +
                    "\n Current name: $branchName"
            )
        }

        if (isFeatureBranch) {
            if (!isFeatureTitle) {
                fail(
                    "Title is not following our pattern:\n" +
                        "\n[issue_id](optional) Add|Change|Remove|Fix|Bump|Security {Feature title}"
                )
            }
        }

        if (isReleaseBranch) {
            if (!isReleaseTitle) {
                fail(
                    "Title is not following our pattern: Prepare Release major.minor.patch (1.2.0)"
                )
            }
        }

        // General
        if (pullRequest.assignee == null) {
            warn("Please assign someone to merge this PR")
        }

        if (pullRequest.milestone == null) {
            warn("Set a milestone please")
        }

        if (pullRequest.body == null || (pullRequest.body as String).length < pullRequestBodyMinLines) {
            warn("Please include a description of your PR changes")
        }

        // Changelog
        if (!isChangelogUpdated) {
            warn("Changes should be reflected in the CHANGELOG.md")
        }

        // Size
        val changes = (pullRequest.additions ?: 0) - (pullRequest.deletions ?: 0)
        if (changes > lineCountFail) {
            fail("This Pull-Request is way to big, please slice it into smaller pull-requests.")
        } else if (changes > lineCountWarn) {
            warn("Too Big Pull-Request, keep changes smaller!")
        } else if (changes > lineCountInform) {
            message("Large Pull-Request, try to keep changes smaller if you can")
        }
    }
}
