/*
 * Copyright (C) 2017 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.red.build.finder;

import java.util.List;

public class BuildStatistics {
    private List<KojiBuild> builds;
    private long numberOfBuilds;
    private long numberOfArchives;
    private long numberOfImportedArchives;
    private long numberOfImportedBuilds;

    public BuildStatistics(List<KojiBuild> builds) {
        builds.forEach(build -> {
            if (build.getBuildInfo().getId() > 0) {
                numberOfBuilds++;

                if (build.isImport()) {
                    numberOfImportedBuilds++;
                }
            }

            if (build.getArchives() != null) {
                long archiveCount = build.getArchives().stream().count();
                numberOfArchives += archiveCount;

                if (build.isImport()) {
                    numberOfImportedArchives += archiveCount;
                }
            }
        });

        this.builds = builds;
    }

    public long getNumberOfBuilds() {
        return numberOfBuilds;
    }

    public long getNumberOfImportedBuilds() {
        return numberOfImportedBuilds;
    }

    public long getNumberOfArchives() {
        return numberOfArchives;
    }

    public long getNumberOfImportedArchives() {
        return numberOfImportedArchives;
    }

    public double getPercentOfBuildsImported() {
        if (builds.size() == 0) {
            return 0;
        }

        return (((double) numberOfImportedBuilds / (double) numberOfBuilds) * 100.00);
    }

    public double getPercentOfArchivesImported() {
        if (builds.size() == 0) {
            return 0;
        }

        return (((double) numberOfImportedArchives / (double) numberOfArchives) * 100.00);
    }
}
