import { Scenario1Module } from './scenario1.module';

describe('Scenario1Module', () => {
  let scenario1Module: Scenario1Module;

  beforeEach(() => {
    scenario1Module = new Scenario1Module();
  });

  it('should create an instance', () => {
    expect(scenario1Module).toBeTruthy();
  });
});
