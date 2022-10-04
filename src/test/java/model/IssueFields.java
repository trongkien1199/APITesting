package model;

public class IssueFields {
    private Fields fields;

    public IssueFields(Fields fields) {
        this.fields = fields;
    }

    public Fields getFields() {
        return fields;
    }

    public static class Fields{
        private Project project;
        private IssueType issuetype;
        private String summary;

        public Project getProject() {
            return project;
        }

        public IssueType getIssuetype() {
            return issuetype;
        }

        public String getSummary() {
            return summary;
        }

        public Fields(Project project, IssueType issuetype, String summary) {
            this.project = project;
            this.issuetype = issuetype;
            this.summary = summary;
        }
    }
    public static class Project{
        private String key;

        public Project(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }
    public static class IssueType{
        private String id;

        public IssueType(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }
}
