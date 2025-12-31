// License: Commons Clause License Condition v1.0[LGPL-2.1-only]

const {exec, spawn, execSync, spawnSync} = require('child_process');
const cp = require('child_process'); 

function testingWithUserInput1(args) {

    // ruleid:javascript_exec_rule-child-process
    exec(`${args[0]}`, (error, stdout, stderr) => {
        console.log("exec output 1: \n", stdout)
    });

    // ruleid:javascript_exec_rule-child-process
    const ls = spawn(`${args[0]}`, ['-lh', '/usr']);
    ls.stdout.on('data', (data) => {
    console.log(`spawn output 1: \n ${data}`);
    });

    // ruleid:javascript_exec_rule-child-process
    var result = execSync(`${args[0]}`).toString();
    console.log("execSync output 1: \n", result);

    // ruleid:javascript_exec_rule-child-process
    var ls2 = spawnSync(`${args[0]}`, ['-l', '/usr'], { encoding : 'utf8' });
    console.log("spawnSync output 1: \n", ls2.stdout);
}
 
function testingWithUserInput2(userInput) {

    // ruleid:javascript_exec_rule-child-process
    cp.exec(userInput, (error, stdout, stderr) => {
        console.log("exec output 2: \n", stdout)
    });

    // ruleid:javascript_exec_rule-child-process
    const ls = cp.spawn(userInput, ['-lh', '/usr']);
    ls.stdout.on('data', (data) => {
    console.log(`spawn output 2: \n ${data}`);
    });

    // ruleid:javascript_exec_rule-child-process
    var result = cp.execSync(userInput).toString();
    console.log("execSync output 2: \n", result);
    
    // ruleid:javascript_exec_rule-child-process
    var ls2 = cp.spawnSync(userInput, ['-l', '/usr'], { encoding : 'utf8' });
    console.log("spawnSync output 2: \n", ls2.stdout);
};

function testingConstantStrings1() {

    // ok:javascript_exec_rule-child-process
    exec('ls', (error, stdout, stderr) => {
        console.log("exec output 3: \n", stdout)
    });

    // ok:javascript_exec_rule-child-process
    const ls = spawn('ls', ['-lh', '/usr']);
    ls.stdout.on('data', (data) => {
    console.log(`spawn output 3: \n ${data}`);
    });

    // ok:javascript_exec_rule-child-process
    var result = execSync('ls').toString();
    console.log("execSync output 3: \n", result);
    
    // ok:javascript_exec_rule-child-process
    var ls2 = spawnSync('ls', ['-l', '/usr'], { encoding : 'utf8' });
    console.log("spawnSync output 3: \n", ls2.stdout);
};

function testingConstantStrings2() {

    // ok:javascript_exec_rule-child-process
    cp.exec('ls', (error, stdout, stderr) => {
        console.log("exec output 4: \n", stdout)
    });

    // ok:javascript_exec_rule-child-process
    const ls = cp.spawn('ls', ['-lh', '/usr']);
    ls.stdout.on('data', (data) => {
    console.log(`spawn output 4: \n ${data}`);
    });

    // ok:javascript_exec_rule-child-process
    var result = cp.execSync('ls').toString();
    console.log("execSync output 4: \n", result);
    
    // ok:javascript_exec_rule-child-process
    var ls2 = cp.spawnSync('ls', ['-l', '/usr'], { encoding : 'utf8' });
    console.log("spawnSync output 4: \n", ls2.stdout);
};

function testingPatternNot1 (userInput) {
    userInput = 'ls'
  
    // ok:javascript_exec_rule-child-process
    cp.exec(userInput, (error, stdout, stderr) => {
      console.log('exec output 2: \n', stdout)
    })
  
    // ok:javascript_exec_rule-child-process
    const ls = cp.spawn(userInput, ['-lh', '/usr'])
    ls.stdout.on('data', data => {
      console.log(`spawn output 2: \n ${data}`)
    })
  
    // ok:javascript_exec_rule-child-process
    var result = cp.execSync(userInput).toString()
    console.log('execSync output 2: \n', result)
  
    // ok:javascript_exec_rule-child-process
    var ls2 = cp.spawnSync(userInput, ['-l', '/usr'], { encoding: 'utf8' })
    console.log('spawnSync output 2: \n', ls2.stdout)
  }
  
  function testingPatternNot2 (input, input2) {
    input2 = ['ls', '-l']
  
    // ruleid:javascript_exec_rule-child-process
    const ls3 = cp.spawn(input[0], [input[1]])
    ls.stdout.on('data', data => {
      console.log(`spawn output 2: \n ${data}`)
    })
  
    // ruleid:javascript_exec_rule-child-process
    var ls4 = cp.spawnSync(input[0], [input[1]], {
      encoding: 'utf8'
    })
    console.log('spawnSync output 2: \n', ls4.stdout)

    input = ['ls', '-l']
  
    // ok:javascript_exec_rule-child-process
    const ls = cp.spawn(input[0], [input[1]])
    ls.stdout.on('data', data => {
      console.log(`spawn output 2: \n ${data}`)
    })
  
    // ok:javascript_exec_rule-child-process
    var ls2 = cp.spawnSync(input[0], [input[1]], {
      encoding: 'utf8'
    })
    console.log('spawnSync output 2: \n', ls2.stdout)
  }
  