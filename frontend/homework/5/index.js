//Question1
const fs = require('fs');
const express=require('express');
const os=require('os');

let app=express();

function sysInfo() {
    return {
        HostName: os.hostname(),
        OperatingSystem: os.platform(),
        Architecture: os.arch(),
        OSRelease: os.release(),
        Uptime: os.uptime(),
        NumberOfCPUCores: os.cpus().length,
        TotalMemory: os.totalmem(),
        FreeMemory: os.freemem(),
        CurrentWorkingDirectory: process.cwd()
    };
}
app.use((req, res, next) => {
    const systemInfo = sysInfo();
    fs.writeFile('sysInfo.json', JSON.stringify(systemInfo), (err) => {
        if (err) {
            console.error('Error writing to file:', err);
            res.status(400).json({ error: 'Bad request' });
        } else {
            console.log('System information written to file.');
            next();
        }
    });
});

const PORT = process.env.PORT || 5000;
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});

app.get('/', (req, res) => {
    fs.readFile('sysInfo.json', (err, data) => {
        if (err) {
            res.status(404).json({ error: 'data not found' });
        } else {
            const systemInfo = JSON.parse(data);
            const message = `Hello, my name is ${os.userInfo().username}!\n Here is my system information:\n${JSON.stringify(systemInfo)}`;
            res.status(200).send(message);
        }
    });
});


//Question2
const path = require('path');

function extractFileInfo(filePath) {
    return {
        extension: path.extname(filePath),
        baseName: path.basename(filePath),
        directory: path.dirname(filePath)
    };
}

function processFilePaths(filePaths) {
    return filePaths.map(filePath => extractFileInfo(filePath));
}

const filePaths = [
    'dir1/dir2/file1.txt',
    'dir1/dir3/file2.txt',
    'dir1/dir3/file3.md',
    'dir4/file4.jpg',
    'dir4/file5.pdf'
];

const output = processFilePaths(filePaths);
console.log(output);
