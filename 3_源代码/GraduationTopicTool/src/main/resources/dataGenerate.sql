USE GraduationTopic;
-- Insert into User table
-- Insert into User table
INSERT INTO User (id, username, password, role) VALUES
('20205080906057', '肖敏', 'password123', 'Teacher'),
('20205080906067', '学生A', 'password123', 'Student'),
('20205080906068', '院长B', 'password123', 'Dean'),
('20205080906069', '学生B', 'password123', 'Student'),
('20205080906070', '老师C', 'password123', 'Teacher'),
('20205080906071', '老师D', 'password123', 'Teacher'),
('20205080906072', '学生C', 'password123', 'Student'),
('20205080906073', '院长C', 'password123', 'Dean'),
('20205080906074', '院长D', 'password123', 'Dean');

-- Insert into Teacher table
INSERT INTO Teacher (id, code, gender, phone, departmentName, professionalTitle, detail) VALUES
('20205080906057', 'T001', '男', '18675881372', '数字媒体技术系', '高级工程师', '研究方向：游戏开发'),
('20205080906070', 'T002', '女', '13987456321', '计算机科学系', '讲师', '研究方向：人工智能'),
('20205080906071', 'T003', '男', '13789654123', '软件工程系', '教授', '研究方向：大数据处理');

-- Insert into Students table
INSERT INTO Students (id, gender, phone, email, major, enrollmentYear) VALUES
('20205080906067', '男', '15113413654', 'studentA@example.com', '数字媒体技术', 2020),
('20205080906069', '女', '15987654321', 'studentB@example.com', '计算机科学', 2021),
('20205080906072', '男', '13876543210', 'studentC@example.com', '软件工程', 2019);

-- Insert into Dean table
INSERT INTO Dean (id, gender, phone, email, departmentName) VALUES
('20205080906068', '男', '13927884636', 'deanB@example.com', '数字媒体技术系'),
('20205080906073', '女', '13567891234', 'deanC@example.com', '计算机科学系'),
('20205080906074', '男', '13678912345', 'deanD@example.com', '软件工程系');

-- Insert into Topic table
INSERT INTO Topic (topicId, teacherId, title, description, sourceOfTopic, researchDirection, theoryAndTechRequirements) VALUES
(2125, '20205080906057', '基于UE4的广州市停车场可视化设计与实现', '使用UE4进行场景构建，模拟停车场的实际情况', '教师出题', '虚拟现实; Unreal Engine', '熟悉UE4引擎使用，了解3D建模与动画制作'),
(2129, '20205080906057', '基于UE4的海洋保护者的游戏设计与制作', '开发一款保护海洋环境的3D游戏', '教师出题', '游戏开发; 3D', 'UE4, 编程'),
(2130, '20205080906070', '基于机器学习的图像分类系统', '使用深度学习技术对图像数据进行分类', '教师出题', '机器学习; 深度学习', '掌握机器学习基本算法，了解Python编程'),
(2131, '20205080906071', '大数据分析平台的设计与实现', '构建一个用于处理和分析大数据的平台', '教师出题', '大数据; 数据挖掘', '掌握Hadoop和Spark的使用，具备Java编程能力');

-- Insert into DeanApproval table
INSERT INTO DeanApproval (approvalId, deanId, topicId, approvalStatus, approvalDate) VALUES
('A001', '20205080906068', 2125, '同意', '2023-09-29'),
('A002', '20205080906068', 2129, '同意', '2023-09-29'),
('A003', '20205080906073', 2130, '待审', '2023-10-05'),
('A004', '20205080906074', 2131, '同意', '2023-10-06');

-- Insert into TopicDetails table
INSERT INTO TopicDetails (detailId, topicId, additionalInfo) VALUES
('D001', 2125, '涉及使用虚幻引擎进行3D场景构建与动画制作'),
('D002', 2129, '开发一款保护海洋环境的游戏，目标是提升大众环保意识'),
('D003', 2130, '该项目需要使用Python和TensorFlow进行深度学习模型训练'),
('D004', 2131, '使用Hadoop和Spark进行数据处理和分析，目标是构建一个可扩展的数据分析平台');
