from playwright.sync_api import sync_playwright

def run(playwright):
    browser = playwright.chromium.launch()
    page = browser.new_page()
    try:
        page.goto("http://localhost:3000/prediction")

        # Wait for page load
        page.wait_for_selector("h1", timeout=10000)

        # Check H1 font size
        # In new layout, it is text-sm lg:text-base => 14px or 16px.
        h1 = page.locator("h1")
        font_size = h1.evaluate("el => window.getComputedStyle(el).fontSize")
        print(f"H1 Font Size: {font_size}")

        # Check layout wrapper (flex flex-col lg:flex-row)
        # We need to find the container that has these classes.
        # It's inside main > div.flex.flex-col.lg:flex-row

        wrapper = page.locator("main > div").last
        display = wrapper.evaluate("el => window.getComputedStyle(el).display")
        print(f"Wrapper Display: {display}")

        # Take screenshot
        page.screenshot(path="verification/prediction_layout.png")
        print("Screenshot taken")

    except Exception as e:
        print(f"Error: {e}")
        exit(1)
    finally:
        browser.close()

with sync_playwright() as playwright:
    run(playwright)
