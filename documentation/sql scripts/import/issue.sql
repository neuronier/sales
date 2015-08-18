INSERT INTO `issuethread` (`clientId`, `status`, `subject`, `threadId`) VALUES
('6cd7e00b-4053-4ad5-ae68-b7f0a4a9207c', 'Ongoing', 'Teszt issue thread', 'cf57e377-5982-4efd-a696-ce30f33f05dd');

INSERT INTO `issuemessage` (`Text`, `date`, `messageId`, `owner`, `threadId`) VALUES
('This is the first issue message in this thread.', '2015-08-18 15:33:53', '15a9bf60-092c-4f6c-bbb1-2bb7383bbfb1', 'rfazekas', 'cf57e377-5982-4efd-a696-ce30f33f05dd'),
('<span style="text-decoration: underline;"><span style="color: rgb(0, 153, 0);">Everything is working fine.</span></span><br>', '2015-08-18 15:34:45', 'd3f3713f-bda4-4cf2-b039-f657869cfda1', 'admin', 'cf57e377-5982-4efd-a696-ce30f33f05dd');