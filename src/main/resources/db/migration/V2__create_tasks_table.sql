CREATE TABLE tasks (id BIGSERIAL PRIMARY KEY,
                    project_id BIGINT NOT NULL,
                    title VARCHAR(150) NOT NULL,
                    description VARCHAR(1000),
                    status VARCHAR(30) NOT NULL,
                    priority VARCHAR(20) NOT NULL,
                    due_date DATE,
                    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
                    CONSTRAINT fk_tasks_project
                    FOREIGN KEY (project_id)
                    REFERENCES projects(id)
                    ON DELETE CASCADE
);

CREATE INDEX idx_tasks_project_id ON tasks(project_id);

