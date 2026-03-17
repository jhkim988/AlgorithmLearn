import argparse
import sys
import os

from keynote2slidev.adapters.keynote_parser_adapter import KeynoteParserAdapter
from keynote2slidev.adapters.slidev_generator_adapter import SlidevGeneratorAdapter
from keynote2slidev.use_cases.convert import ConvertKeynoteToSlidevUseCase

def main():
    parser = argparse.ArgumentParser(description="Convert an Apple Keynote presentation into a Slidev markdown project.")
    parser.add_argument("input", help="Path to the .key presentation file")
    parser.add_argument("output", help="Path to the output directory")

    args = parser.parse_args()

    if not os.path.exists(args.input):
        print(f"Error: File '{args.input}' not found.")
        sys.exit(1)

    print(f"Converting '{args.input}' to '{args.output}'...")

    # Initialize adapters
    keynote_parser = KeynoteParserAdapter()
    slidev_generator = SlidevGeneratorAdapter()

    # Initialize use case
    use_case = ConvertKeynoteToSlidevUseCase(
        parser=keynote_parser,
        generator=slidev_generator
    )

    try:
        output_file = use_case.execute(args.input, args.output)
        print(f"Success! Slidev presentation generated at '{output_file}'")
        print(f"Run 'npx slidev {output_file}' to start the presentation server.")
    except Exception as e:
        print(f"Error during conversion: {e}")
        sys.exit(1)

if __name__ == "__main__":
    main()
