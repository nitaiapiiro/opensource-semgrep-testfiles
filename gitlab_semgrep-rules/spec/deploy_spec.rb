require_relative '../ci/deploy'

describe 'deploy' do
  let(:version) { '1.0.0' }
  let(:mappings_path) { 'qa/fixtures/deploy/default/mappings' }
  let(:rule_path) { 'qa/fixtures/deploy/default' }

  let(:dist_path) do
    description = self.class.description.downcase.strip.gsub(/[^0-9a-z_\-\s]/, '').squeeze(' ').gsub(' ', '-')
    "tmp/test-#{RSpec.configuration.seed}/#{description}"
  end

  subject { Deploy.new(mappings_path, rule_path, dist_path, version) }

  before(:each) do
    FileUtils.mkdir_p(dist_path)
  end

  context 'with test rules' do
    before { subject.run }

    it 'generates expected distribution YAML' do
      exist_code = system('diff', '-rq', 'qa/expect/deploy/with-test-rules', dist_path)
      expect(exist_code).to be_truthy
    end
  end
end